package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.config.annotation.JsonRequest;
import github.devparkge.realworld.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.controller.UserResponseAddUp;
import github.devparkge.realworld.controller.request.UpdateUserRequest;
import github.devparkge.realworld.controller.response.UserResponse;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.service.UserService;
import github.devparkge.realworld.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public UserResponse currentUser(
            @JwtAuthenticationRequired UUID authUserUUID
    ) {
        User user = userService.getCurrentUser(authUserUUID);
        return UserResponseAddUp.from(user);
    }

    @PutMapping()
    public UserResponse updateUser(
            @JwtAuthenticationRequired UUID authUserUUID,
            @JsonRequest("user") UpdateUserRequest updateUserRequest
    ) {
        User user = userService.updateUser(
                authUserUUID,
                updateUserRequest.email(),
                updateUserRequest.username(),
                updateUserRequest.password(),
                updateUserRequest.bio(),
                updateUserRequest.image()
        );
        return UserResponseAddUp.from(user);
    }
}
