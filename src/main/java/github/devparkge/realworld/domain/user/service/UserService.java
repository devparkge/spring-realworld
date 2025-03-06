package github.devparkge.realworld.domain.user.service;

import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean jwtAuthenticationByUUID(UUID uuid) {
        return userRepository.findByUUID(uuid)
                .isPresent();
    }

    public User updateUser(
            UUID uuid,
            String email,
            String username,
            String password,
            String bio,
            String image
    ) {
        User updateUser = userRepository.findByUUID(uuid)
                .map(user -> user.update(uuid, email, username, password, bio, image))
                .orElseThrow(() -> new UUIDNotFoundException("존재하지 않는 아이디입니다."));

        return userRepository.saveUser(updateUser);
    }
}
