package github.devparkge.realworld.domain.repository;

import github.devparkge.realworld.domain.model.Follower;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InMemoryFollowerRepository implements FollowerRepository{
    public List<Follower> followers = new ArrayList<>();

    @Override
    public boolean isFollow(String username, UUID uuid) {
        return false;
    }

    public void clear() {
        followers = new ArrayList<>();
    }
}
