package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.controller.config.JsonRequest;
import github.devparkge.realworld.controller.request.LoginRequest;
import github.devparkge.realworld.controller.request.SignUpRequest;
import github.devparkge.realworld.controller.response.CurrentUserResponse;
import github.devparkge.realworld.controller.response.LoginResponse;
import github.devparkge.realworld.controller.response.SignUpResponse;
import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.service.dto.LoginDto;
import github.devparkge.realworld.service.dto.SignUpDto;
import github.devparkge.realworld.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(
            @JsonRequest("user") LoginRequest loginRequest
    ) {
        LoginDto loginDto = userService.login(
                loginRequest.email(),
                loginRequest.password()
        );
        return LoginResponse.from(loginDto);
    }

    @PostMapping()
    public SignUpResponse signUp(
            @JsonRequest("user") SignUpRequest signUpRequest
    ) {
        SignUpDto signUpDto  = userService.signUp(signUpRequest.username(), signUpRequest.email(), signUpRequest.password());
        return SignUpResponse.from(signUpDto);
    }

    @GetMapping
    public CurrentUserResponse currentUser(
            HttpServletRequest request
    ) {
        UUID uuid = (UUID) request.getAttribute("UUID");
        String token = jwtUtil.generateToken(uuid);
        User user = userService.getCurrentUser(uuid);

        return CurrentUserResponse.from(user, token);
    }
}
