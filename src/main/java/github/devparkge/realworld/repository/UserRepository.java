package github.devparkge.realworld.repository;

import github.devparkge.realworld.repository.domain.User;

import java.util.Optional;

public interface UserRepository {
    //Login email 검증
    boolean findEmail(String email);

    //Login password 확인
    Optional<User> getUserToEmail(String email, String password);
}