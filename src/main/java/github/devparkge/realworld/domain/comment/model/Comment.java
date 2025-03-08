package github.devparkge.realworld.domain.comment.model;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.user.model.User;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
public record Comment(
        int id,
        Instant createdAt,
        Instant updatedAt,
        String body,
        User author,
        Article article
) {
    public static Comment create(User author, Article article, String body) {
        Instant now = Instant.now();
        return Comment.builder()
                .id(-1)
                .createdAt(now)
                .updatedAt(now)
                .body(body)
                .author(author)
                .article(article)
                .build();
    }

    public Comment updateId(int id) {
        return Comment.builder()
                .id(id)
                .createdAt(createdAt())
                .updatedAt(updatedAt())
                .body(body())
                .author(author())
                .article(article())
                .build();
    }
}
