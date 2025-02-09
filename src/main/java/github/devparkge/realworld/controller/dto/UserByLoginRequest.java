package github.devparkge.realworld.controller.dto;

public record UserByLoginRequest(
        String email,
        String password
) {
}