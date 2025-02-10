package github.devparkge.realworld.service;

import github.devparkge.realworld.controller.response.LoginResponse;
import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.domain.repository.UserRepository;
import github.devparkge.realworld.exception.EmailNotFoundException;
import github.devparkge.realworld.exception.InvalidPasswordException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public LoginResponse login(String email, String password) {
        User user = userRepository.findEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("유효하지 않은 이메일입니다."));

        validatePassword(user, password);

        return LoginResponse.from(user);
    }

    private void validatePassword(User user, String password) {
        if (!user.matchesPassword(password)) {
            throw new InvalidPasswordException("유효하지 않은 비밀번호입니다.");
        }
    }
}
