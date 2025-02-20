package github.devparkge.realworld.controller;

import github.devparkge.realworld.controller.response.UserResponse;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class UserResponseAddUp {
    private static JwtUtil jwtUtil;

    public static UserResponse from(User user) {
        String token = jwtUtil.generateToken(user.uuid());
        return UserResponse.from(user, token);
    }
}
