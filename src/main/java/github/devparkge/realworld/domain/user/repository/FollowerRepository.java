package github.devparkge.realworld.domain.user.repository;

import java.util.UUID;

public interface FollowerRepository {
    boolean isFollow(String username, UUID uuid);
    void follow(String username, UUID uuid);

}
