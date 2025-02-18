package github.devparkge.realworld.domain.repository;

import java.util.UUID;

public interface FollowerRepository {
    boolean isFollow(String username, UUID uuid);
}
