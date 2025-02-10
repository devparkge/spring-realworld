package github.devparkge.realworld.repository.mapper;

import github.devparkge.realworld.repository.UserRepository;
import github.devparkge.realworld.service.dto.SelectUserToPasswordDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements UserRepository {
    @Override
    public boolean findEmail(String email) {
        return true;
    }

    @Override
    public SelectUserToPasswordDto getUserToEmail(String email, String password) {
        return new SelectUserToPasswordDto(
                "jake@jake.jake",
                "jwt.token.here",
                "jake",
                "I work at statefarm",
                null
        );
    }
}
