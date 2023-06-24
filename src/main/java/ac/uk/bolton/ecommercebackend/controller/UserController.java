package ac.uk.bolton.ecommercebackend.controller;

import ac.uk.bolton.ecommercebackend.dto.UserDTO;
import ac.uk.bolton.ecommercebackend.service.UserService;
import ac.uk.bolton.ecommercebackend.util.AjaxResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public AjaxResponse<Object> getCurrentUser() {
        return AjaxResponse.success(userService.getCurrentUser());
    }

    @PutMapping("/me")
    public AjaxResponse<Object> updateCurrentUser(@RequestBody UserDTO userDTO) {
        return AjaxResponse.success(userService.updateCurrentUser(userDTO));
    }
}
