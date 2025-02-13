package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.controller.config.JsonRequest;
import github.devparkge.realworld.controller.request.LoginRequest;
import github.devparkge.realworld.controller.response.CurrentUserResponse;
import github.devparkge.realworld.controller.response.LoginResponse;
import github.devparkge.realworld.exception.InvalidTokenException;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.service.dto.GetCurrentUserDto;
import github.devparkge.realworld.service.dto.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public CurrentUserResponse currentUser(
            HttpServletRequest request
    ) {
        String header = ((HttpServletRequest) request).getHeader("Authorization");
        String token = (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
        String email = (String) request.getAttribute("email");
        if(email == null) throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        GetCurrentUserDto user = userService.getCurrentUserDto(email, token);

        return CurrentUserResponse.from(user);
    }
}
