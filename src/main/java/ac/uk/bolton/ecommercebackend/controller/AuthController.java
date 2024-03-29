package ac.uk.bolton.ecommercebackend.controller;

import ac.uk.bolton.ecommercebackend.dto.TokenDTO;
import ac.uk.bolton.ecommercebackend.request.UpdatePasswordRequest;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.exception.InternalServerErrorException;
import ac.uk.bolton.ecommercebackend.request.LoginRequest;
import ac.uk.bolton.ecommercebackend.request.SignupRequest;
import ac.uk.bolton.ecommercebackend.service.EmailService;
import ac.uk.bolton.ecommercebackend.service.UserService;
import ac.uk.bolton.ecommercebackend.util.AjaxResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<ResponsePayload> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(signupRequest));
    }


    @PostMapping("/login")
    public AjaxResponse<Object> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (authentication.isAuthenticated()) {
                TokenDTO tokenDTO = userService.login(authentication, request, loginRequest);
                return AjaxResponse.success(tokenDTO);
            }
        } catch (AuthenticationException e) {
            if (e.getMessage().equals("Bad credentials")) {
                throw new BadCredentialsException("Invalid username or password");
            }

            if (e.getMessage().equals("Incorrect result size: expected 1, actual 0")) {
                throw new BadCredentialsException("Invalid username or password");
            }

            throw new InternalServerErrorException("Unknown error occurred");
        } catch (Exception e) {
            return AjaxResponse.error("Unknown error occurred");
        }

        return AjaxResponse.error(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("forgot-password")
    public AjaxResponse<Object> forgotPassword(@RequestBody Map<String, String> map) {
        try {
            if (!map.containsKey("email")) {
                throw new Exception("Email is required");
            }
            userService.forgotPassword(map.get("email"));
        } catch (Exception e) {
            System.out.println(e);
            return AjaxResponse.error("Unknown error occurred");
        }
        return AjaxResponse.success();
    }

    @PostMapping("update-password")
    public AjaxResponse<Object> updatePassword(@RequestBody UpdatePasswordRequest request) {
        try {
            userService.updatePassword(request);
        } catch (Exception e) {
            return AjaxResponse.error("Unknown error occurred");
        }
        return AjaxResponse.success();
    }
}
