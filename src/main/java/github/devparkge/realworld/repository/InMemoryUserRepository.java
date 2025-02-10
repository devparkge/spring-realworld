package github.devparkge.realworld.repository;

import github.devparkge.realworld.repository.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = List.of(
            new User("jake@jake.jake", "jakejake", "jake", "I work at statefarm", null)
    );

    @Override
    public Optional<User> findEmail(String email) {
        return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }
}
