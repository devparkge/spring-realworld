package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.service.dto.UpdateUserDto;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public record UpdateUserResponse(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
    public static UpdateUserResponse from(UpdateUserDto updateUserDto) {
        return new UpdateUserResponse(
                updateUserDto.email(),
                updateUserDto.token(),
                updateUserDto.username(),
                updateUserDto.bio(),
                updateUserDto.image()
        );
    }
}
