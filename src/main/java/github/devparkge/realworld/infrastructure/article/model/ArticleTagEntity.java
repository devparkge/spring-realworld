package github.devparkge.realworld.infrastructure.article.model;

import github.devparkge.realworld.domain.article.model.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Objects;

@Entity(name = "article_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ArticleTagEntity {
    @Id
    @Column(name = "article_tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer databaseId;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "articleId", nullable = false)
    private ArticleEntity articleEntity;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tagId", nullable = false)
    private TagEntity tagEntity;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public ArticleTagEntity(Integer databaseId, TagEntity tagEntity) {
        this.databaseId = databaseId;
        this.tagEntity = tagEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleTagEntity that = (ArticleTagEntity) o;
        return Objects.equals(databaseId, that.databaseId) && Objects.equals(articleEntity, that.articleEntity) && Objects.equals(tagEntity, that.tagEntity) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(databaseId, articleEntity, tagEntity, createdAt, updatedAt);
    }

    public Tag toDomain() {
        return tagEntity.toDomain();
    }

    public static ArticleTagEntity from(TagEntity tagEntity) {
        return new ArticleTagEntity(
                null,
                tagEntity
        );
    }
}
