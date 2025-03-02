package github.devparkge.realworld.domain.article.model;

import github.devparkge.realworld.domain.user.model.User;

import java.time.LocalDateTime;

public record Comment(
        int id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String body,
        User author,
        Article article
) {
}
