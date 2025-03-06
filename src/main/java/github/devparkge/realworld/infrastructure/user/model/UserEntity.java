package github.devparkge.realworld.infrastructure.user.model;

import github.devparkge.realworld.domain.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    private UUID userId;
    private String email;
    private String password;
    private String username;
    private String bio;
    private String image;

    public UserEntity(UUID userId, String email, String password, String username, String bio, String image) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(username, that.username) && Objects.equals(bio, that.bio) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, username, bio, image);
    }

    public static UserEntity from(User user) {
        return new UserEntity(
                user.uuid(),
                user.email(),
                user.password(),
                user.username(),
                user.bio(),
                user.image()
        );
    }

    public User toDomain() {
        return new User(
                this.userId,
                this.email,
                this.password,
                this.username,
                this.bio,
                this.image
        );
    }
}
