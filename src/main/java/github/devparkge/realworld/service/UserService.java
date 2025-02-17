package github.devparkge.realworld.service;

import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.domain.repository.UserRepository;
import github.devparkge.realworld.exception.DuplicateEmailException;
import github.devparkge.realworld.exception.EmailNotFoundException;
import github.devparkge.realworld.exception.InvalidPasswordException;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import github.devparkge.realworld.service.dto.LoginDto;
import github.devparkge.realworld.service.dto.SignUpDto;
import github.devparkge.realworld.service.dto.UpdateUserDto;
import github.devparkge.realworld.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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
        String token = jwtUtil.generateToken(user.uuid());

        return LoginDto.from(user, token);
    }

    private void validatePassword(User user, String password) {
        if (!user.matchesPassword(password)) {
            throw new InvalidPasswordException("유효하지 않은 비밀번호입니다.");
        }
    }

    public boolean jwtAuthenticationByUUID(UUID uuid) {
        return userRepository.findByUUID(uuid)
                .isPresent();
    }

    public User getCurrentUser(UUID uuid) {
        return userRepository.findByUUID(uuid)
                .orElseThrow(() -> new UUIDNotFoundException("유효하지 않은 아이디입니다."));
    }

    @Transactional
    public SignUpDto signUp(String username, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) throw new DuplicateEmailException("중복된 이메일 입니다.");
        User user = userRepository.saveUser(username, email, password);
        String token = jwtUtil.generateToken(user.uuid());

        return SignUpDto.from(user, token);
    }

    public UpdateUserDto updateUser(
            UUID uuid,
            Optional<String> email,
            Optional<String> username,
            Optional<String> password,
            Optional<String> bio,
            Optional<String> image
    ) {
        String token = jwtUtil.generateToken(uuid);
        User user = userRepository.findByUUID(uuid)
                .orElseThrow(() -> new UUIDNotFoundException("존재하지 않는 아이디입니다."));
        User updateUser = User.updateUser(user, email, username, password, bio, image);

        return UpdateUserDto.from(
                userRepository.updateUser(
                        uuid,
                        updateUser.email(),
                        updateUser.username(),
                        updateUser.password(),
                        updateUser.bio(),
                        updateUser.image()
                ), token);
    }
}
