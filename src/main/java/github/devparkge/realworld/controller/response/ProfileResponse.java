package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.service.dto.FollowUserDto;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("profile")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public record ProfileResponse(
        String username,
        String bio,
        String image,
        boolean following
) {
    public static ProfileResponse from(User user, boolean isFollowing) {
        return new ProfileResponse(
                user.username(),
                user.bio(),
                user.image(),
                isFollowing
        );
    }
}
