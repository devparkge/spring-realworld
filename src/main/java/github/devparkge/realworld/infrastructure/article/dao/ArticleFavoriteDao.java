package github.devparkge.realworld.infrastructure.article.dao;

import github.devparkge.realworld.infrastructure.article.model.ArticleEntity;
import github.devparkge.realworld.infrastructure.article.model.FavoriteEntity;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ArticleFavoriteDao extends JpaRepository<FavoriteEntity, UUID> {
    void deleteByUserEntityAndArticleEntity(UserEntity userEntity, ArticleEntity articleEntity);
    @Query(
            """
            SELECT COUNT(*) as conut
            FROM article_favorite af
                 INNER JOIN article a ON a = af.articleEntity
                 INNER JOIN users u ON u = af.userEntity
            WHERE af.articleEntity = :articleId
            """
    )
    int conutByArticleEntity(UUID articleId);
}
