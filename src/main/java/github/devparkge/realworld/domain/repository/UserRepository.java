package github.devparkge.realworld.domain.repository;

import github.devparkge.realworld.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByEmail(String email);

    Optional<User> findByUUID(UUID uuid);

    Optional<User> findByUsername(String username);

    User saveUser(String username, String email, String password);

    User updateUser(User updateUser);
}