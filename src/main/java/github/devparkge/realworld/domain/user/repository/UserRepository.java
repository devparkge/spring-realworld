package github.devparkge.realworld.domain.user.repository;

import github.devparkge.realworld.domain.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends UserReadRepository {
    User saveUser(User saveUser);

    User updateUser(User updateUser);

    void follow(UUID followerId, UUID followeeId);

    void unFollow(UUID followerId, UUID followeeId);
}