package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.config.annotation.JsonRequest;
import github.devparkge.realworld.controller.UserResponseAddUp;
import github.devparkge.realworld.controller.request.LoginRequest;
import github.devparkge.realworld.controller.request.SignUpRequest;
import github.devparkge.realworld.controller.response.SignUpResponse;
import github.devparkge.realworld.controller.response.UserResponse;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.service.UsersService;
import github.devparkge.realworld.domain.user.service.dto.SignUpDto;
import github.devparkge.realworld.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersApiController {
    private final UsersService usersService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public UserResponse login(
            @JsonRequest("user") LoginRequest loginRequest
    ) {
        User user = usersService.login(
                loginRequest.email(),
                loginRequest.password()
        );
        return UserResponseAddUp.from(user);
    }

    @PostMapping()
    public SignUpResponse signUp(
            @JsonRequest("user") SignUpRequest signUpRequest
    ) {
        SignUpDto signUpDto = usersService.signUp(signUpRequest.username(), signUpRequest.email(), signUpRequest.password());
        return SignUpResponse.from(signUpDto);
    }
}
