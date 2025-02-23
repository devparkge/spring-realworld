package github.devparkge.realworld.domain.user.service;

import github.devparkge.realworld.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;

    public Boolean isFollowing(String username, UUID uuid) {
        return Optional.ofNullable(uuid)
                .map(id -> userRepository.isFollowing(username, id))
                .orElse(false);
    }

    public void followUser(String username, UUID uuid) {
        userRepository.follow(username, uuid);
    }

    public void unFollowUser(String username, UUID uuid) {
        userRepository.unFollow(username, uuid);
    }
}
