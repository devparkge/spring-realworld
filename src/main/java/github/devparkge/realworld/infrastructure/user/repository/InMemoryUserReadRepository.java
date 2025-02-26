package github.devparkge.realworld.infrastructure.user.repository;

import github.devparkge.realworld.infrastructure.user.model.Follow;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserReadRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InMemoryUserReadRepository implements UserReadRepository {
    protected List<User> users = new ArrayList<>();
    protected List<Follow> follows = new ArrayList<>();

    @Override
    public boolean isFollowing(UUID followerId, UUID followeeId) {
        return follows.stream()
                .anyMatch(
                        follow ->
                                follow.followeeId().equals(followeeId) && follow.followerId().equals(followerId)
                );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }

    @Override
    public Optional<User> findByUUID(UUID uuid) {
        return users.stream()
                .filter(user -> user.uuid().equals(uuid))
                .findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }


    public void clear() {
        users = new ArrayList<>();
        follows = new ArrayList<>();
    }
}
