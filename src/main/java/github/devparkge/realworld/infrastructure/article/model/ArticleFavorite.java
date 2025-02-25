package github.devparkge.realworld.infrastructure.article.model;

import java.util.UUID;

public record ArticleFavorite(
        UUID favoritId,
        UUID userId,
        UUID articleId
) {
}
