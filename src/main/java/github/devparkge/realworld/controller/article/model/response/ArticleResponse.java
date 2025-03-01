package github.devparkge.realworld.controller.article.model.response;

import github.devparkge.realworld.controller.article.model.Author;
import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponse(
        String slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean favorited,
        int favoritesCount,
        Author author
) {
    public static ArticleResponse from(Article article, boolean favorited, int favoritesCount, boolean isFollowing) {
        return new ArticleResponse(
                article.slug().value(),
                article.title(),
                article.description(),
                article.body(),
                article.tagList(),
                article.createdAt(),
                article.updatedAt(),
                favorited,
                favoritesCount,
                Author.from(article.author(), isFollowing)
        );
    }
}
