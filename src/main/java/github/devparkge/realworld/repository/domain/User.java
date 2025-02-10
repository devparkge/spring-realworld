package github.devparkge.realworld.repository.domain;

public record User(
        String email,
        String password,
        String username,
        String bio,
        String image
) {
}
