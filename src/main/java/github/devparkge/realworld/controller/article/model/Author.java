package github.devparkge.realworld.controller.article.model;

import github.devparkge.realworld.domain.user.model.User;

public record Author(
        String username,
        String bio,
        String image,
        boolean isFollowing
) {
    public static Author from(User user, boolean isFollowing) {
        return new Author(
                user.username(),
                user.bio(),
                user.image(),
                isFollowing
        );
    }
}
