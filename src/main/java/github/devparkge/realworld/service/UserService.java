package github.devparkge.realworld.service;

import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.domain.repository.UserRepository;
import github.devparkge.realworld.exception.DuplicateEmailException;
import github.devparkge.realworld.exception.EmailNotFoundException;
import github.devparkge.realworld.exception.InvalidPasswordException;
import github.devparkge.realworld.service.dto.GetCurrentUserDto;
import github.devparkge.realworld.service.dto.LoginDto;
import github.devparkge.realworld.util.JwtUtil;
import github.devparkge.realworld.service.dto.SignUpDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public LoginDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("유효하지 않은 이메일입니다."));

        validatePassword(user, password);
        String token = jwtUtil.generateToken(email);

        return LoginDto.from(user, token);
    }

    private void validatePassword(User user, String password) {
        if (!user.matchesPassword(password)) {
            throw new InvalidPasswordException("유효하지 않은 비밀번호입니다.");
        }
    }

    public boolean jwtAuthenticationByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElse(null);
        return (user != null) ? true : false;
    }

    public GetCurrentUserDto getCurrentUserDto(String email, String token) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("유효하지 않은 이메일입니다."));
        return GetCurrentUserDto.from(user, token);
    }

    @Transactional
    public SignUpDto signUp(String username, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) throw new DuplicateEmailException("중복된 이메일 입니다.");

        return SignUpDto.from(userRepository.saveUser(User.signUp(
                email,
                password,
                username
        )));
    }
}
