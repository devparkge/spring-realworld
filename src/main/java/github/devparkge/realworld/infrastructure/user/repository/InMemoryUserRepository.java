package github.devparkge.realworld.infrastructure.user.repository;

import github.devparkge.realworld.infrastructure.user.model.Follower;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class InMemoryUserRepository extends InMemoryUserReadRepository implements UserRepository {

    @Override
    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(
            User updateUser
    ) {

        users.stream()
                .filter(user -> user.uuid().equals(updateUser.uuid()))
                .map(user -> user == updateUser);

        return updateUser;
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

}
