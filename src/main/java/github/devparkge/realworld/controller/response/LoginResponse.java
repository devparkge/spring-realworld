package github.devparkge.realworld.controller.response;

import github.devparkge.realworld.controller.dto.UserDto;
import lombok.Getter;

@Getter
public class LoginResponse {
    private UserDto user;

    public LoginResponse(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                '}';
    }
}
