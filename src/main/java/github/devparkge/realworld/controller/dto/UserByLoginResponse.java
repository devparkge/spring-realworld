package github.devparkge.realworld.controller.dto;

public record UserByLoginResponse(
    String email,
    String token,
    String username,
    String bio,
    String image
) {
}
