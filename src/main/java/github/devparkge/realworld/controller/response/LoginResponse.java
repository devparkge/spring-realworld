package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

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
    @Override
    public String toString() {
        return "LoginResponse{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
