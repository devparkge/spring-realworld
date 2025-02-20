package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.config.annotation.JwtAuthenticationOptional;
import github.devparkge.realworld.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.controller.response.FollowUserResponse;
import github.devparkge.realworld.controller.response.GetProfileResponse;
import github.devparkge.realworld.domain.user.service.ProfilesService;
import github.devparkge.realworld.domain.user.service.dto.FollowUserDto;
import github.devparkge.realworld.domain.user.service.dto.GetProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfilesApiController {
    private final ProfilesService profilesService;

    @GetMapping("/{username}")
    public GetProfileResponse getProfilse(
            @JwtAuthenticationOptional UUID authUserUUID,
            @PathVariable("username") String username
    ) {
        GetProfileDto getProfileDto = profilesService.getProfile(username, authUserUUID);
        return GetProfileResponse.from(getProfileDto);
    }

    @PostMapping("/{username}/follow")
    public FollowUserResponse folloUser(
            @JwtAuthenticationRequired UUID uuid,
            @PathVariable("username") String username
    ) {
        FollowUserDto followUserDto = profilesService.followUser(username, uuid);
        return FollowUserResponse.from(followUserDto);
    }
}
