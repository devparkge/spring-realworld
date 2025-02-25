package github.devparkge.realworld.domain.user.repository;

import github.devparkge.realworld.domain.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserReadRepository {
    Optional<User> findByEmail(String email);

    Optional<User> findByUUID(UUID uuid);

    Optional<User> findByUsername(String username);

    boolean isFollowing(UUID followerId, UUID followeeId);
}
