package github.devparkge.realworld.infrastructure.article.model;

import github.devparkge.realworld.domain.article.model.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity {
    @Id
    private UUID tagId;
    @Column(nullable = false, unique = true)
    private String tagName;
    private Instant createdAt;
    private Instant updatedAt = Instant.now();

    public TagEntity(UUID tagId, String tagName, Instant createdAt) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        return Objects.equals(tagId, tagEntity.tagId) && Objects.equals(tagName, tagEntity.tagName) && Objects.equals(createdAt, tagEntity.createdAt) && Objects.equals(updatedAt, tagEntity.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagName, createdAt, updatedAt);
    }

    public Tag toDomain() {
        return new Tag(
                tagId,
                tagName,
                createdAt,
                updatedAt
        );
    }

    public static TagEntity from(Tag tag) {
        return new TagEntity(
                tag.tagId(),
                tag.tagName(),
                tag.createdAt()
        );
    }
}
