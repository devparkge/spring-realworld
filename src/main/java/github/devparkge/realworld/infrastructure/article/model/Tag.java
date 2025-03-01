package github.devparkge.realworld.infrastructure.article.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Tag (
        UUID tagId,
        String tagName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static Tag create(String tagName) {
        LocalDateTime now = LocalDateTime.now();
        return new Tag(
                UUID.randomUUID(),
                tagName,
                now,
                now
        );
    }
}
