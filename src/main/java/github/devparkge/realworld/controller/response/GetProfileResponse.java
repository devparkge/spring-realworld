package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.service.dto.GetProfileDto;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("profile")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public record GetProfileResponse(
        String username,
        String bio,
        String image,
        boolean following
) {
    public static GetProfileResponse from(GetProfileDto getProfileDto) {
        return new GetProfileResponse(
                getProfileDto.username(),
                getProfileDto.bio(),
                getProfileDto.image(),
                getProfileDto.following()
        );
    }
}
