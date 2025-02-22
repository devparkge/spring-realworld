package github.devparkge.realworld.controller.user;

import github.devparkge.realworld.controller.user.response.UserResponse;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class UserResponseAddUp {

    public static UserResponse from(User user, JwtUtil jwtUtil) {
        String token = jwtUtil.generateToken(user.uuid());
        return UserResponse.from(user, token);
    }
}
