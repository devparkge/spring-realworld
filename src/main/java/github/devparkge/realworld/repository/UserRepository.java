package github.devparkge.realworld.repository;

import github.devparkge.realworld.repository.domain.User;

import java.util.Optional;

public interface UserRepository {
    boolean findEmail(String email);

    Optional<User> getUserToEmail(String email, String password);
}