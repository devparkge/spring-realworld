package github.devparkge.realworld.domain.model;

import java.util.UUID;

public record Follower(
        UUID followerUUID,
        UUID uuid,
        String username
) {
}
