package github.devparkge.realworld.domain.model;

import lombok.Builder;
import java.util.UUID;

@Builder
public record Follower(
        UUID followerUUID,
        UUID uuid,
        String username
) {
    public static Follower follow(UUID followerUUID, UUID uuid, String username) {
        return Follower.builder()
                .followerUUID(followerUUID)
                .uuid(uuid)
                .username(username)
                .build();
    }
}
