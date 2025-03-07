package github.devparkge.realworld.infrastructure.article.dao;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.infrastructure.article.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleDao extends JpaRepository<ArticleEntity, UUID> {
    Optional<ArticleEntity> findBySlug(Slug slug);
    @Query(
            """
            SELECT a.id
            FROM article a
                 INNER JOIN
                 a.favoriteEntities af
                 INNER JOIN
                 af.userEntity u
            WHERE u.id = :userId
            """
    )
    List<UUID> getFavoriteArticles(UUID userId);
    @Query(
            """
            SELECT a
            FROM users u
                 INNER JOIN user_follow uf ON uf.followerEntity = u
                 INNER JOIN article a ON a.authorEntity = uf.followeeEntity
            WHERE u.id = :userId
            """
    )
    List<ArticleEntity> findFeedArticles(UUID userId);
}
