package github.devparkge.realworld.controller.user.model.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.domain.user.model.User;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public record UserResponse(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static UserResponse from(User user, String token) {
        return new UserResponse(
                user.email(),
                token,
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
