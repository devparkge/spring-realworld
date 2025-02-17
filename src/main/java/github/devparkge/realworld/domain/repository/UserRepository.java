package github.devparkge.realworld.domain.repository;

import github.devparkge.realworld.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    Optional<User> findByUUID(UUID uuid);
    User saveUser(String username, String email, String password);
    User updateUser(UUID uuid, String email, String username, String password, String bio, String image);
}