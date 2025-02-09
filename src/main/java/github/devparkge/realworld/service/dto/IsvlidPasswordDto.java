package github.devparkge.realworld.service.dto;

public record IsvlidPasswordDto(
        String email,
        String token,
        String username,
        String bio,
        String image
) {
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
