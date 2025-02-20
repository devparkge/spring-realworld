package github.devparkge.realworld.domain.user.service;

import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.EmailNotFoundException;
import github.devparkge.realworld.exception.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final UserRepository userRepository;

    public User getByEmail(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("유효하지 않은 이메일입니다."));
        validatePassword(user, password);
        return user;
    }

    private void validatePassword(User user, String password) {
        if (!user.matchesPassword(password)) {
            throw new InvalidPasswordException("유효하지 않은 비밀번호입니다.");
        }
    }
}
