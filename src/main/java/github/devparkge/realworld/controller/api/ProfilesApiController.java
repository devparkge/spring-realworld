package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.controller.config.annotation.JwtAuthenticationOptional;
import github.devparkge.realworld.controller.response.GetProfileResponse;
import github.devparkge.realworld.service.ProfilesService;
import github.devparkge.realworld.service.dto.GetProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
