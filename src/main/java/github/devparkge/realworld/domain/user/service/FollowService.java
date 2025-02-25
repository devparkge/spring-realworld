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

    public Boolean isFollowing(UUID followerId, UUID followeeId) {
        return Optional.ofNullable(followeeId)
                .map(id -> userRepository.isFollowing(followerId, followeeId))
                .orElse(false);
    }

    public void followUser(UUID followerId, UUID followeeId) {
        userRepository.follow(followerId, followeeId);
    }

    public void unFollowUser(UUID followerId, UUID followeeId) {
        userRepository.unFollow(followerId, followeeId);
    }
}
