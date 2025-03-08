package github.devparkge.realworld.controller.user.api;

import github.devparkge.realworld.config.annotation.JwtAuthenticationOptional;
import github.devparkge.realworld.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.controller.user.model.response.ProfileResponse;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.service.FollowService;
import github.devparkge.realworld.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfilesApiController {
    private final FollowService followService;
    private final GetUserService getUserService;

    @GetMapping("/{username}")
    public ProfileResponse getProfile(
            @JwtAuthenticationOptional UUID authUserUUID,
            @PathVariable("username") String username
    ) {
        User user = getUserService.getByUsername(username);
        boolean isFollowing = followService.isFollowing(user.uuid(), authUserUUID);
        return ProfileResponse.from(user, isFollowing);
    }

    @PostMapping("/{username}/follow")
    public ProfileResponse followUser(
            @JwtAuthenticationRequired UUID authUserUUID,
            @PathVariable("username") String username
    ) {
        User user = getUserService.getByUsername(username);
        followService.followUser(user.uuid(), authUserUUID);
        boolean isFollowing = followService.isFollowing(user.uuid(), authUserUUID);
        return ProfileResponse.from(user, isFollowing);
    }

    @DeleteMapping("/{username}/follow")
    public ProfileResponse unFollowUser(
            @JwtAuthenticationOptional UUID authUserUUID,
            @PathVariable("username") String username
    ) {
        User user = getUserService.getByUsername(username);
        followService.unFollowUser(user.uuid(), authUserUUID);
        boolean isFollowing = followService.isFollowing(user.uuid(), authUserUUID);
        return ProfileResponse.from(user, isFollowing);
    }
}
