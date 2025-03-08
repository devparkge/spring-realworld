package github.devparkge.realworld.infrastructure.comment.model;

import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.infrastructure.article.model.ArticleEntity;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Entity(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer databaseId;
    private String body;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorId")
    private UserEntity author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "articleId")
    private ArticleEntity articleEntity;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public CommentEntity(Integer databaseId, String body, UserEntity author, ArticleEntity articleEntity) {
        this.databaseId = databaseId;
        this.body = body;
        this.author = author;
        this.articleEntity = articleEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(databaseId, that.databaseId) && Objects.equals(body, that.body) && Objects.equals(author, that.author) && Objects.equals(articleEntity, that.articleEntity) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(databaseId, body, author, articleEntity, createdAt, updatedAt);
    }

    public Comment toDomain() {
        return new Comment(
                databaseId,
                createdAt,
                updatedAt,
                body,
                author.toDomain(),
                articleEntity.toDomain()
        );
    }

    public static CommentEntity from(Comment comment, UserEntity author, ArticleEntity articleEntity) {
        return new CommentEntity(
                null,
                comment.body(),
                author,
                articleEntity
        );
    }
}
