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

    public static User signUp(String username, String password, String email) {
        return new User(email, password, username, null, null);
    }
}
