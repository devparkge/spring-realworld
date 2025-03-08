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

    public Boolean isFollowing(UUID followeeId, UUID followerId) {
        return Optional.ofNullable(followerId)
                .map(id -> userRepository.isFollowing(followeeId, followerId))
                .orElse(false);
    }

    public void followUser(UUID followeeId, UUID followerId) {
        userRepository.follow(followeeId, followerId);
    }

    public void unFollowUser(UUID followeeId, UUID followerId) {
        userRepository.unFollow(followeeId, followerId);
    }
}
