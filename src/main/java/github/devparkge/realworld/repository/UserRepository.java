package github.devparkge.realworld.repository;

import github.devparkge.realworld.repository.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findEmail(String email);
}