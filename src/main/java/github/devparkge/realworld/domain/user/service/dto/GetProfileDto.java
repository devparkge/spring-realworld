package github.devparkge.realworld.domain.user.service.dto;

import github.devparkge.realworld.domain.user.model.User;

public record GetProfileDto(
        String username,
        String bio,
        String image,
        boolean following
) {
    public static GetProfileDto from(User user, boolean isFollow) {
        return new GetProfileDto(
                user.username(),
                user.bio(),
                user.image(),
                isFollow
        );
    }
}
