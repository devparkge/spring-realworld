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
    public Optional<User> findEmail(String email) {
        return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }

    @Override
    public void addUser(String username, String email, String password) {
        users.add(
                new User(
                        email,
                        password,
                        username,
                        "I work at statefarm",
                        null
                )
        );
    }
}
