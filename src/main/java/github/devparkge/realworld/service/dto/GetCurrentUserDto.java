package github.devparkge.realworld.service.dto;

import github.devparkge.realworld.domain.model.User;

public record GetCurrentUserDto(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static GetCurrentUserDto from(User user, String token) {
        return new GetCurrentUserDto(
                user.email(),
                token,
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
