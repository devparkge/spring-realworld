package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.config.annotation.JsonRequest;
import github.devparkge.realworld.controller.request.LoginRequest;
import github.devparkge.realworld.controller.request.SignUpRequest;
import github.devparkge.realworld.controller.response.LoginResponse;
import github.devparkge.realworld.controller.response.SignUpResponse;
import github.devparkge.realworld.domain.user.service.UsersService;
import github.devparkge.realworld.domain.user.service.dto.LoginDto;
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
    public LoginResponse login(
            @JsonRequest("user") LoginRequest loginRequest
    ) {
        LoginDto loginDto = usersService.login(
                loginRequest.email(),
                loginRequest.password()
        );
        return LoginResponse.from(loginDto);
    }

    @PostMapping()
    public SignUpResponse signUp(
            @JsonRequest("user") SignUpRequest signUpRequest
    ) {
        SignUpDto signUpDto = usersService.signUp(signUpRequest.username(), signUpRequest.email(), signUpRequest.password());
        return SignUpResponse.from(signUpDto);
    }
}
