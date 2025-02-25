package github.devparkge.realworld.infrastructure.user.repository;

import github.devparkge.realworld.infrastructure.user.model.Follow;
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
    public void follow(UUID followerId, UUID followeeId) {
        follows.add(Follow.follow(
                UUID.randomUUID(),
                followeeId,
                followerId
        ));
    }

    @Override
    public void unFollow(UUID followerId, UUID followeeId) {
        follows.stream()
                .filter(
                        follow ->
                                follow.followeeId().equals(followeeId) && follow.followerId().equals(followerId)
                ).findFirst()
                .map(follower -> follows.remove(follower));
    }

}
