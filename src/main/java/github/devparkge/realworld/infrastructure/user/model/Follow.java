package github.devparkge.realworld.infrastructure.user.model;

import lombok.Builder;
import java.util.UUID;

@Builder
public record Follow(
        UUID followId,
        UUID followeeId,
        UUID followerId
) {
    public static Follow follow(UUID followId, UUID followeeId, UUID followerId) {
        return Follow.builder()
                .followId(followId)
                .followeeId(followeeId)
                .followerId(followerId)
                .build();
    }
}
