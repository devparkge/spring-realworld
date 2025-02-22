package github.devparkge.realworld.controller.user.model.request;

public record LoginRequest(
        String email,
        String password
) {
}
