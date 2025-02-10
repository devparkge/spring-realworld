package github.devparkge.realworld.domain.repository;

import github.devparkge.realworld.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findEmail(String email);
}