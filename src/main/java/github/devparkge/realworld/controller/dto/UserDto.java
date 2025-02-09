package github.devparkge.realworld.controller.dto;

public record UserDto(
    String email,
    String token,
    String username,
    String bio,
    String image
) {
}
