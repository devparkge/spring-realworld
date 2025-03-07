package github.devparkge.realworld.domain.article.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record Tag (
        UUID tagId,
        String tagName,
        Instant createdAt,
        Instant updatedAt
) {
    public static Tag create(String tagName) {
        Instant now = Instant.now();
        return new Tag(
                UUID.randomUUID(),
                tagName,
                now,
                now
        );
    }
}
