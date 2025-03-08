package github.devparkge.realworld.infrastructure.article.dao;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.infrastructure.article.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleDao extends JpaRepository<ArticleEntity, UUID> {
    Optional<ArticleEntity> findBySlug(Slug slug);
    @Query(
            """
            SELECT a.id
            FROM article_favorite af
                 INNER JOIN af.articleEntity a
                 INNER JOIN af.userEntity u
            WHERE u.id = :userId
            """
    )
    List<UUID> getFavoriteArticles(@Param("userId") UUID userId);
    @Query(
            """
            SELECT a
            FROM article a
                 INNER JOIN a.authorEntity author
                 INNER JOIN user_follow uf ON uf.followeeEntity = author
            WHERE uf.followerEntity.id = :userId
            """
    )
    List<ArticleEntity> findFeedArticles(@Param("userId") UUID userId);
}
