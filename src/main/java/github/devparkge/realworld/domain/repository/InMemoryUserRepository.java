package github.devparkge.realworld.domain.repository;

import github.devparkge.realworld.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class InMemoryUserRepository implements UserRepository {
    private final List<User> users;

    public InMemoryUserRepository() {
        this.users = new ArrayList<>(List.of(
                new User("jake@jake.jake", "jakejake", "jake", "I work at statefarm", null)
        ));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }

    @Override
    public User saveUser(User user) {
        users.add(user);
        return user;
    }
}
