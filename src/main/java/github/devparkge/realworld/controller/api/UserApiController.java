package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.controller.dto.UserByLoginResponse;
import github.devparkge.realworld.controller.request.LoginRequest;
import github.devparkge.realworld.controller.response.LoginResponse;
import github.devparkge.realworld.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest loginRequest
    ) {
        UserByLoginResponse user = userService.login(
                loginRequest.user().email(),
                loginRequest.user().password()
        );
        LoginResponse loginResponse = new LoginResponse(user);
        System.out.println("UserController Response : " + loginResponse);
        return loginResponse;
    }
}
