package github.devparkge.realworld.domain.model;


import lombok.Builder;

import java.util.Optional;
import java.util.UUID;

@Builder
public record User(
        UUID uuid,
        String email,
        String password,
        String username,
        String bio,
        String image
) {
    public static User signUp(UUID uuid, String username, String password, String email) {
        return User.builder()
                .uuid(uuid)
                .username(username)
                .password(password)
                .email(email)
                .build();
    }

    public static User updateUser(
            User user,
            Optional<String> email,
            Optional<String> username,
            Optional<String> password,
            Optional<String> bio,
            Optional<String> image
    ) {
        return User.builder()
                .email(email.orElse(user.email()))
                .username(username.orElse(user.username()))
                .password(password.orElse(user.password()))
                .bio(bio.orElse(user.bio()))
                .image(image.orElse(user.image()))
                .build();
    }

    public boolean matchesPassword(String password) {
        return password().equals(password);
    }

}
