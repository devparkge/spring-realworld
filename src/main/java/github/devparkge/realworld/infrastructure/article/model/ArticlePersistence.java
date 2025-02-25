package github.devparkge.realworld.infrastructure.article.model;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ArticlePersistence(
        UUID uuid,
        UUID authorId,
        Slug slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ArticlePersistence from(Article article) {
        return new ArticlePersistence(
                article.uuid(),
                article.author().uuid(),
                article.slug(),
                article.title(),
                article.description(),
                article.body(),
                article.tagList(),
                article.createdAt(),
                article.updatedAt()
        );
    }

    public Article toDomain(User author) {
        return new Article(
                uuid,
                author,
                slug,
                title,
                description,
                body,
                tagList,
                createdAt,
                updatedAt
        );
    }
}
