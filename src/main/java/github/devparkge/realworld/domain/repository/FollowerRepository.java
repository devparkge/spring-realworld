package github.devparkge.realworld.domain.repository;

import github.devparkge.realworld.domain.model.Follower;

import java.util.UUID;

public interface FollowerRepository {
    boolean isFollow(String username, UUID uuid);
    Follower follow(String username, UUID uuid);
}
