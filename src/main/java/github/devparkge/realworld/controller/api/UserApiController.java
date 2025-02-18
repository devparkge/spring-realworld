package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.controller.config.annotation.JsonRequest;
import github.devparkge.realworld.controller.config.annotation.JwtAuthentication;
import github.devparkge.realworld.controller.request.UpdateUserRequest;
import github.devparkge.realworld.controller.response.CurrentUserResponse;
import github.devparkge.realworld.controller.response.UpdateUserResponse;
import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.service.dto.UpdateUserDto;
import github.devparkge.realworld.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public CurrentUserResponse currentUser(
            @JwtAuthentication UUID authUserUUID
    ) {
        String token = jwtUtil.generateToken(authUserUUID);
        User user = userService.getCurrentUser(authUserUUID);

        return CurrentUserResponse.from(user, token);
    }

    @PutMapping()
    public UpdateUserResponse updateUser(
            @JwtAuthentication UUID authUserUUID,
            @JsonRequest("user") UpdateUserRequest updateUserRequest
    ) {
        UpdateUserDto updateUserDto = userService.updateUser(
                authUserUUID,
                updateUserRequest.email(),
                updateUserRequest.username(),
                updateUserRequest.password(),
                updateUserRequest.bio(),
                updateUserRequest.image()
        );
        return UpdateUserResponse.from(updateUserDto);
    }
}
