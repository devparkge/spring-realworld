package github.devparkge.realworld.repository.domain;

public record User(
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
