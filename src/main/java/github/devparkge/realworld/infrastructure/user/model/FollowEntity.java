package github.devparkge.realworld.infrastructure.user.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Entity(name = "user_follow")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowEntity {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer databaseId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followee_id")
    private UserEntity followeeEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id")
    private UserEntity followerEntity;
    private Instant createdAt;
    private Instant updatedAt = Instant.now();

    public FollowEntity(Integer databaseId, UserEntity followeeEntity, UserEntity followerEntity, Instant createdAt) {
        this.databaseId = databaseId;
        this.followeeEntity = followeeEntity;
        this.followerEntity = followerEntity;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowEntity that = (FollowEntity) o;
        return Objects.equals(databaseId, that.databaseId) && Objects.equals(followeeEntity, that.followeeEntity) && Objects.equals(followerEntity, that.followerEntity) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(databaseId, followeeEntity, followerEntity, createdAt, updatedAt);
    }

    public static FollowEntity of(UserEntity followeeEntity, UserEntity followerEntity) {
        return new FollowEntity(
                null,
                followeeEntity,
                followerEntity,
                Instant.now()
        );
    }

}
