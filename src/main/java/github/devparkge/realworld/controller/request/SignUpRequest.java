package github.devparkge.realworld.controller.request;

public record SignUpRequest(
        String username,
        String email,
        String password
) {
}
