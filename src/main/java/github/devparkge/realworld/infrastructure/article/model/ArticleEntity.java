package github.devparkge.realworld.infrastructure.article.model;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.infrastructure.article.model.converter.SlugConverter;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity(name = "article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ArticleEntity {
    @Id
    private UUID articleId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorId")
    private UserEntity authorEntity;
    @Convert(converter = SlugConverter.class)
    private Slug slug;
    private String title;
    private String description;
    private String body;
    @OneToMany(mappedBy = "articleEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleTagEntity> tagEntities = new ArrayList<>();
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public ArticleEntity(UUID articleId, UserEntity authorEntity, Slug slug, String title, String description, String body, List<ArticleTagEntity> tagEntities) {
        this.articleId = articleId;
        this.authorEntity = authorEntity;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagEntities = tagEntities;
        this.tagEntities.forEach(articleTagEntity -> articleTagEntity.setArticleEntity(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return Objects.equals(articleId, that.articleId) && Objects.equals(authorEntity, that.authorEntity) && Objects.equals(slug, that.slug) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(body, that.body) && Objects.equals(tagEntities, that.tagEntities) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, authorEntity, slug, title, description, body, tagEntities, createdAt, updatedAt);
    }

    public Article toDomain() {
        return new Article(
                articleId,
                authorEntity.toDomain(),
                slug,
                title,
                description,
                body,
                tagEntities.stream()
                        .map(ArticleTagEntity::toDomain)
                        .toList(),
                createdAt,
                updatedAt
        );
    }

    public static ArticleEntity from(Article article, List<TagEntity> tagEntities, UserEntity userEntity) {
        return new ArticleEntity(
                article.uuid(),
                userEntity,
                article.slug(),
                article.title(),
                article.description(),
                article.body(),
                tagEntities.stream()
                        .map(ArticleTagEntity::from)
                        .toList()
        );
    }
    public static ArticleEntity from(Article article, List<ArticleTagEntity> tagEntities) {
        return new ArticleEntity(
                article.uuid(),
                UserEntity.from(article.author()),
                article.slug(),
                article.title(),
                article.description(),
                article.body(),
                tagEntities
        );
    }

    public void update(Article article) {
        this.slug = article.slug();
        this.title = article.title();
        this.description = article.description();
        this.body = article.body();
    }
}
