package github.devparkge.realworld.service.dto;

import github.devparkge.realworld.controller.response.LoginResponse;
import github.devparkge.realworld.domain.model.User;

public record LoginDto(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static LoginDto from(User user) {
        return new LoginDto(
                user.email(),
                "jwt.token.here",
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
