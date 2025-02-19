package github.devparkge.realworld.service;

import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.domain.repository.FollowerRepository;
import github.devparkge.realworld.domain.repository.UserRepository;
import github.devparkge.realworld.exception.UsernameNotFoundException;
import github.devparkge.realworld.service.dto.GetProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfilesService {
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;

    public GetProfileDto getProfile(String username, UUID uuid) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException( username + "은 존재하지 않는 유저명 입니다."));
        return GetProfileDto.from(user, isFollow(username, uuid));
    }

    private Boolean isFollow(String username, UUID uuid) {
        return Optional.ofNullable(uuid)
                .map(id -> followerRepository.isFollow(username, id))
                .orElse(false);
    }
}
