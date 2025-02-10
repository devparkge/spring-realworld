package github.devparkge.realworld.repository.mapper;

import github.devparkge.realworld.repository.UserRepository;
import github.devparkge.realworld.service.dto.SelectUserToPasswordDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements UserRepository {
    @Override
    public boolean selectEmail(String email) {
        return true;
    }

    @Override
    public SelectUserToPasswordDto selectUserToPassword(String email, String password) {
        return new SelectUserToPasswordDto(
                "jake@jake.jake",
                "jwt.token.here",
                "jake",
                "I work at statefarm",
                null
        );
    }
}
