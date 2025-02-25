package github.devparkge.realworld.controller.user.model.request;

public record SignUpRequest(
        String username,
        String email,
        String password
) {
}
