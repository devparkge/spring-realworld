package github.devparkge.realworld.domain.article.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Tag(
        UUID tagId,
        String tagName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static Tag add(String tagName) {
        LocalDateTime now = LocalDateTime.now();
        return new Tag(
                UUID.randomUUID(),
                tagName,
                now,
                now
        );
    }
}
