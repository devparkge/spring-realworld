package github.devparkge.realworld.infrastructure.article.model;

import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "article_favorite")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteEntity {
    @Id
    private UUID favoritId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private ArticleEntity articleEntity;

    public FavoriteEntity(UUID favoritId, UserEntity userEntity, ArticleEntity articleEntity) {
        this.favoritId = favoritId;
        this.userEntity = userEntity;
        this.articleEntity = articleEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteEntity that = (FavoriteEntity) o;
        return Objects.equals(favoritId, that.favoritId) && Objects.equals(userEntity, that.userEntity) && Objects.equals(articleEntity, that.articleEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoritId, userEntity, articleEntity);
    }

    public static FavoriteEntity from(UserEntity userEntity, ArticleEntity articleEntity) {
        return new FavoriteEntity(
                UUID.randomUUID(),
                userEntity,
                articleEntity
        );
    }
}
