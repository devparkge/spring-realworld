package github.devparkge.realworld.domain.user.service.dto;

import github.devparkge.realworld.domain.user.model.User;

public record LoginDto(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static LoginDto from(User user, String token) {
        return new LoginDto(
                user.email(),
                token,
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
