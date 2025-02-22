package github.devparkge.realworld.controller.user.request;

public record LoginRequest(
        String email,
        String password
) {
}
