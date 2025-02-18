package github.devparkge.realworld.service;

import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.domain.repository.UserRepository;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import github.devparkge.realworld.service.dto.UpdateUserDto;
import github.devparkge.realworld.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public boolean jwtAuthenticationByUUID(UUID uuid) {
        return userRepository.findByUUID(uuid)
                .isPresent();
    }

    public User getCurrentUser(UUID uuid) {
        return userRepository.findByUUID(uuid)
                .orElseThrow(() -> new UUIDNotFoundException("유효하지 않은 아이디입니다."));
    }

    public UpdateUserDto updateUser(
            UUID uuid,
            String email,
            String username,
            String password,
            String bio,
            String image
    ) {
        String token = jwtUtil.generateToken(uuid);
        User updateUser = userRepository.findByUUID(uuid)
                .map(user -> user.update(email, username, password, bio, image))
                .orElseThrow(() -> new UUIDNotFoundException("존재하지 않는 아이디입니다."));

        return UpdateUserDto.from(userRepository.updateUser(updateUser), token);
    }
}
