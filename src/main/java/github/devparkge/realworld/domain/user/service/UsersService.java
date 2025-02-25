package github.devparkge.realworld.domain.user.service;

import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.DuplicateEmailException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersService {
    private final UserRepository userRepository;

    @Transactional
    public User signUp(String username, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) throw new DuplicateEmailException("중복된 이메일 입니다.");
        return userRepository.saveUser(
                User.signUp(
                        UUID.randomUUID(),
                        username,
                        password,
                        email
                )
        );
    }
}
