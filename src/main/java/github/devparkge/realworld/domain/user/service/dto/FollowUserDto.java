package github.devparkge.realworld.domain.user.service.dto;

import github.devparkge.realworld.domain.user.model.User;

public record FollowUserDto(
        String username,
        String bio,
        String image,
        boolean following
) {
    public static FollowUserDto from(User user, boolean following) {
        return new FollowUserDto(
                user.username(),
                user.bio(),
                user.image(),
                following
        );
    }
}
