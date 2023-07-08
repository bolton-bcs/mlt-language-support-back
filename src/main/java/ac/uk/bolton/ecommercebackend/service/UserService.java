package ac.uk.bolton.ecommercebackend.service;

import ac.uk.bolton.ecommercebackend.dto.TokenDTO;
import ac.uk.bolton.ecommercebackend.dto.UserDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.request.LoginRequest;
import ac.uk.bolton.ecommercebackend.request.SignupRequest;
import ac.uk.bolton.ecommercebackend.request.UpdatePasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
public interface UserService {
    ResponsePayload save(SignupRequest signupRequest);

    TokenDTO login(Authentication authentication, HttpServletRequest request, LoginRequest loginDTO);

    UserDTO getCurrentUser();

    UserDTO updateCurrentUser(UserDTO userDTO);

    void forgotPassword(String email) throws Exception;

    UserDTO updatePassword(UpdatePasswordRequest request) throws Exception;
}
