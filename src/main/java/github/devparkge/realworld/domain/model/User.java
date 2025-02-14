package github.devparkge.realworld.domain.model;


import java.util.UUID;

public record User(
        UUID uuid,
        String email,
        String password,
        String username,
        String bio,
        String image
) {
    public static User signUp(UUID uuid, String username, String password, String email) {
        return new User(uuid, email, password, username, null, null);
    }
    public boolean matchesPassword(String password) {
        return password().equals(password);
    }

}
