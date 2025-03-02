package github.devparkge.realworld.domain.comment.model;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.user.model.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Comment(
        int id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String body,
        User author,
        Article article
) {
    public static Comment create(User author, Article article, String body) {
        LocalDateTime now = LocalDateTime.now();
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
