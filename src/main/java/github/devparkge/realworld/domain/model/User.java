package github.devparkge.realworld.domain.model;


import lombok.Builder;

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

    public boolean matchesPassword(String password) {
        return password().equals(password);
    }

}
