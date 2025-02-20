package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.domain.user.service.dto.FollowUserDto;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("profile")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public record FollowUserResponse(
        String username,
        String bio,
        String image,
        boolean following
) {
    public static FollowUserResponse from(FollowUserDto followUserDto) {
        return new FollowUserResponse(
                followUserDto.username(),
                followUserDto.bio(),
                followUserDto.image(),
                followUserDto.following()
        );
    }
}
