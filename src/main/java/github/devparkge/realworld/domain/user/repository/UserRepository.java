package github.devparkge.realworld.domain.user.repository;

import github.devparkge.realworld.domain.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends UserReadRepository {
    User saveUser(String username, String email, String password);

    User updateUser(User updateUser);

    void follow(String username, UUID uuid);

    void unFollow(String username, UUID uuid);
}