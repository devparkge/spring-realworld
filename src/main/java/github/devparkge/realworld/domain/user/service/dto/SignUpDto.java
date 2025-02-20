package github.devparkge.realworld.domain.user.service.dto;

import github.devparkge.realworld.domain.user.model.User;


public record SignUpDto(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static SignUpDto from(User user, String token) {
        return new SignUpDto(
                user.email(),
                token,
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
