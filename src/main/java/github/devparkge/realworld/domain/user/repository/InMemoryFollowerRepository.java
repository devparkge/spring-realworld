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
    public void follow(String username, UUID uuid) {
        followers.add(Follower.follow(
                UUID.randomUUID(),
                uuid,
                username
        ));
    }

    @Override
    public void unFollow(String username, UUID uuid) {
        followers.stream()
                .filter(
                        follower ->
                                follower.uuid().equals(uuid) && follower.username().equals(username)
                ).findFirst()
                .map(follower -> followers.remove(follower));
    }

    public void clear() {
        followers = new ArrayList<>();
    }
}
