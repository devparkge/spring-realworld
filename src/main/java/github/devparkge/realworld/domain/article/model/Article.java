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
}
