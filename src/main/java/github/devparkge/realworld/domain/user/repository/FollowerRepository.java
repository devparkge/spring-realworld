package github.devparkge.realworld.domain.user.repository;

import github.devparkge.realworld.domain.user.model.Follower;

import java.util.UUID;

public interface FollowerRepository {
    boolean isFollow(String username, UUID uuid);
    Follower follow(String username, UUID uuid);
}
