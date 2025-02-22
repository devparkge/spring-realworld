package github.devparkge.realworld.domain.article.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record Article(
        UUID uuid,
        UUID userId,
        String slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean favorited,
        int favoritesCount
) {
    public static Article addArticle(UUID userId, String title, String description, String body, List<String> tagList) {
        return Article.builder()
                .uuid(UUID.randomUUID())
                .userId(userId)
                .slug(title.toLowerCase())
                .title(title)
                .description(description)
                .body(body)
                .tagList(tagList)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .favorited(false)
                .favoritesCount(0)
                .build();
    }
}
