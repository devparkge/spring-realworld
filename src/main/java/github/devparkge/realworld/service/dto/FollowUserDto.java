package github.devparkge.realworld.service.dto;

import github.devparkge.realworld.domain.model.User;

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
