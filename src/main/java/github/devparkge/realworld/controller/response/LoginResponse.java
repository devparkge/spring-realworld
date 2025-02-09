package github.devparkge.realworld.controller.response;

import github.devparkge.realworld.controller.dto.UserByLoginResponse;
import lombok.Getter;

@Getter
public class LoginResponse {
    private UserByLoginResponse user;

    public LoginResponse(UserByLoginResponse user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                '}';
    }
}
