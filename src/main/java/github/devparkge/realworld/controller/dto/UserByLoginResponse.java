package github.devparkge.realworld.controller.dto;

import lombok.Getter;

@Getter
public class UserByLoginResponse {
    private final String email;
    private final String token;
    private final String username;
    private final String bio;
    private final String image;

    public UserByLoginResponse(String email, String token, String username, String bio, String image) {
        this.email = email;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }
}
