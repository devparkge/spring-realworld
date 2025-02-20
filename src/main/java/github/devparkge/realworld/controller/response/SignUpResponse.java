package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.domain.user.service.dto.SignUpDto;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public record SignUpResponse(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static SignUpResponse from(SignUpDto user) {
        return new SignUpResponse(
                user.email(),
                user.token(),
                user.username(),
                user.bio(),
                user.image()
        );
    }
}
