package github.devparkge.realworld.domain.user.repository;

import github.devparkge.realworld.domain.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends UserReadRepository {
    User saveUser(User saveUser);

    User updateUser(User updateUser);

    void follow(String username, UUID uuid);

    void unFollow(String username, UUID uuid);
}