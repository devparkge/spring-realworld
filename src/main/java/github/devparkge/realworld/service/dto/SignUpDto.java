package github.devparkge.realworld.service.dto;

import github.devparkge.realworld.domain.model.User;

public record SignUpDto(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static SignUpDto from(User user) {
        return new SignUpDto(
                user.email(),
                "",
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
