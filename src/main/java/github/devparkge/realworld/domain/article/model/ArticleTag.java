package github.devparkge.realworld.domain.article.model;

import java.util.UUID;

public record ArticleTag(
        UUID articleTagId,
        UUID articleId,
        UUID tagId
) {
    public static ArticleTag add(UUID articleId, UUID tagId) {
        return new ArticleTag(
                UUID.randomUUID(),
                articleId,
                tagId
        );
    }
}
