package github.devparkge.realworld.service;

import github.devparkge.realworld.controller.response.LoginResponse;
import github.devparkge.realworld.exception.EmailNotFoundException;
import github.devparkge.realworld.exception.InvalidPasswordException;
import github.devparkge.realworld.repository.mapper.UserMapper;
import github.devparkge.realworld.service.dto.IsvlidPasswordDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    @Transactional
    public LoginResponse login(String email, String password) {
        if(!userMapper.isValidEmail(email)) {
            throw new EmailNotFoundException("유효하지 않은 이메일입니다.");
        }

        IsvlidPasswordDto result = userMapper.isValidPassword(email,password);
        if(result == null) {
            throw new InvalidPasswordException("유효하지 않은 비밀번호입니다.");
        }

        return new LoginResponse(
                result.email(),
                result.token(),
                result.username(),
                result.bio(),
                result.image()
        );
    }
}
