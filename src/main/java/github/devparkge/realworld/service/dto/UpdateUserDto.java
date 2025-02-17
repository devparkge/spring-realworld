package github.devparkge.realworld.service.dto;

import github.devparkge.realworld.domain.model.User;

public record UpdateUserDto(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static UpdateUserDto from(User user, String token) {
        return new UpdateUserDto(
                user.email(),
                token,
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
