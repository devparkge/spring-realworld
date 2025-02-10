package github.devparkge.realworld.domain.model;


public record User(
        String email,
        String password,
        String username,
        String bio,
        String image
) {
    public boolean matchesPassword(String password) {
        return password().equals(password);
    }
}
