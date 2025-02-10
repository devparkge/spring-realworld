package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.domain.model.User;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public record LoginResponse(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static LoginResponse from(User user) {
        return new LoginResponse(
                user.email(),
                "jwt.token.here",
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
