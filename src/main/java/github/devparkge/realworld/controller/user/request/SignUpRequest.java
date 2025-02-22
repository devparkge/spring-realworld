package github.devparkge.realworld.controller.user.request;

public record SignUpRequest(
        String username,
        String email,
        String password
) {
}
