package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.domain.user.model.User;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public record CurrentUserResponse(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static CurrentUserResponse from(User user, String token) {
        return new CurrentUserResponse(
                user.email(),
                token,
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
