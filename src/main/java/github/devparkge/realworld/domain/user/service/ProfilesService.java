package github.devparkge.realworld.domain.user.service;

import github.devparkge.realworld.domain.user.repository.FollowerRepository;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfilesService {
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;

    public Boolean isFollowing(String username, UUID uuid) {
        return Optional.ofNullable(uuid)
                .map(id -> followerRepository.isFollow(username, id))
                .orElse(false);
    }

    public void followUser(String username, UUID uuid) {
        followerRepository.follow(username, uuid);
    }
}
