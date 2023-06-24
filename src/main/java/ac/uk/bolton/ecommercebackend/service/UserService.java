package ac.uk.bolton.ecommercebackend.service;

import ac.uk.bolton.ecommercebackend.dto.TokenDTO;
import ac.uk.bolton.ecommercebackend.dto.UserDTO;
import ac.uk.bolton.ecommercebackend.request.LoginRequest;
import ac.uk.bolton.ecommercebackend.request.SignupRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
public interface UserService {
    UserDTO save(SignupRequest signupRequest);

    TokenDTO login(Authentication authentication, HttpServletRequest request, LoginRequest loginDTO);

    UserDTO getCurrentUser();

    UserDTO updateCurrentUser(UserDTO userDTO);
}