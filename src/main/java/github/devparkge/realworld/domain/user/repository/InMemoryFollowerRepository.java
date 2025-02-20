package github.devparkge.realworld.domain.user.repository;

import github.devparkge.realworld.domain.user.model.Follower;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InMemoryFollowerRepository implements FollowerRepository {
    public List<Follower> followers = new ArrayList<>();

    @Override
    public boolean isFollow(String username, UUID uuid) {
        return followers.stream()
                .anyMatch(
                        follower ->
                                follower.uuid().equals(uuid) && follower.username().equals(username)
                );

    }

    @Override
    public Follower follow(String username, UUID uuid) {
        Follower follower = Follower.follow(
                UUID.randomUUID(),
                uuid,
                username
        );
        followers.add(follower);
        return follower;
    }

    public void clear() {
        followers = new ArrayList<>();
    }
}
