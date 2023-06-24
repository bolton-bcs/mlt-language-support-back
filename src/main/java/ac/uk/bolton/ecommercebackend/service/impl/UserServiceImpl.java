package ac.uk.bolton.ecommercebackend.service.impl;

import ac.uk.bolton.ecommercebackend.dto.TokenDTO;
import ac.uk.bolton.ecommercebackend.dto.UserDTO;
import ac.uk.bolton.ecommercebackend.entity.User;
import ac.uk.bolton.ecommercebackend.enums.RoleType;
import ac.uk.bolton.ecommercebackend.exception.InternalServerErrorException;
import ac.uk.bolton.ecommercebackend.repository.UserRepository;
import ac.uk.bolton.ecommercebackend.request.LoginRequest;
import ac.uk.bolton.ecommercebackend.request.SignupRequest;
import ac.uk.bolton.ecommercebackend.service.UserService;
import ac.uk.bolton.ecommercebackend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    private final JwtUtil jwtUtils;

    @Override
    public UserDTO save(SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        User user = mapper.map(signupRequest, User.class);
        user.setRole(RoleType.ROLE_USER.name());
        User registeUser = userRepository.save(user);
        return mapper.map(registeUser, UserDTO.class);
    }

    @Override
    public TokenDTO login(Authentication authentication, HttpServletRequest request, LoginRequest loginDTO) {
        TokenDTO tokenDTO = new TokenDTO();

        try {
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String accessToken = jwtUtils.generateToken(authentication.getName(), request.getServletPath(), roles);
            String refreshToken = jwtUtils.generateRefreshToken(authentication.getName(), request.getServletPath(), roles);
            Long tokenExpirationTime = jwtUtils.getTokenExpirationTime(accessToken);

            tokenDTO.setAccess_token(accessToken);
            tokenDTO.setRefresh_token(refreshToken);
            tokenDTO.setExpires_in(tokenExpirationTime);
            tokenDTO.setToken_type("Bearer");

        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
        return tokenDTO;
    }

    @Override
    public UserDTO getCurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Jwt principal = (Jwt) auth.getPrincipal();
        String email = principal.getClaim("sub");

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return  mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateCurrentUser(UserDTO userDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Jwt principal = (Jwt) auth.getPrincipal();
        String email = principal.getClaim("sub");

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        user.setName(userDTO.getName() == null && userDTO.getName().isEmpty() ? null : userDTO.getName());
        user.setPhone(userDTO.getPhone() == null && userDTO.getPhone().isEmpty() ? null : userDTO.getPhone());
        user.setAddress(user.getAddress() == null && userDTO.getAddress().isEmpty() ? null : userDTO.getAddress());

        User updatedUser = userRepository.save(user);

        return mapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null) {
            String message = String.format("User %s not found", username);
            throw new UsernameNotFoundException(message);
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

            RoleType userRole = RoleType.valueOf(user.getRole());

            if (StringUtils.hasText(user.getRole()) && userRole != null) {
                authorities.add(new SimpleGrantedAuthority(userRole.name()));
            } else {
                throw new UsernameNotFoundException("User role not found");
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .build();
        }
    }
}
