package github.devparkge.realworld.repository.mapper;

import github.devparkge.realworld.repository.UserRepository;
import github.devparkge.realworld.service.dto.IsvlidPasswordDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements UserRepository {
    @Override
    public boolean isValidEmail(String email) {
        return true;
    }

    @Override
    public IsvlidPasswordDto isValidPassword(String email, String password) {
        return new IsvlidPasswordDto(
                "jake@jake.jake",
                "jwt.token.here",
                "jake",
                "I work at statefarm",
                null
        );
    }
}
