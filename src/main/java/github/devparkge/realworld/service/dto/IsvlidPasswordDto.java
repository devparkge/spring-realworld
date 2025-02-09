package github.devparkge.realworld.service.dto;

import lombok.Getter;

@Getter
public class IsvlidPasswordDto {
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    public IsvlidPasswordDto(String email, String token, String username, String bio, String image) {
        this.email = email;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    @Override
    public String toString() {
        return "IsvlidPasswordDto{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
