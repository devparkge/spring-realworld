package github.devparkge.realworld.repository.mapper;

import github.devparkge.realworld.repository.UserRepository;
import github.devparkge.realworld.repository.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements UserRepository {
    @Override
    public boolean findEmail(String email) {
        return true;
    }

    @Override
    public User getUserToEmail(String email, String password) {
        return new User(
                "jake@jake.jake",
                "jwt.token.here",
                "jake",
                "I work at statefarm",
                null
        );
    }
}
