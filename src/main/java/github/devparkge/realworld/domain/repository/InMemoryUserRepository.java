package github.devparkge.realworld.domain.repository;

import github.devparkge.realworld.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();

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
    public User saveUser(String username, String email, String password) {
        User user = User.signUp(
                UUID.randomUUID(),
                username,
                password,
                email
        );
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
}
