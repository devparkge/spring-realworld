package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.controller.config.JsonRequest;
import github.devparkge.realworld.controller.request.LoginRequest;
import github.devparkge.realworld.controller.response.LoginResponse;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.service.dto.LoginDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;

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
}
