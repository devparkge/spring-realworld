package github.devparkge.realworld.infrastructure.comment.dao;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.infrastructure.comment.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentDao extends JpaRepository<CommentEntity, Long> {
    @Query(
            """
            SELECT c
            FROM comment c
                INNER JOIN c.articleEntity a
                INNER JOIN c.author u
            WHERE a.slug = :slug
            """
    )
    List<CommentEntity> findByArticleSlug(@Param("slug") Slug slug);
}
