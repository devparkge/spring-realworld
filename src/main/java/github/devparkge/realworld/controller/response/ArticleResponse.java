package github.devparkge.realworld.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import github.devparkge.realworld.controller.Author;
import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("article")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
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
    public static ArticleResponse from(Article article, User user, boolean isFollowing) {
        return new ArticleResponse(
                article.slug(),
                article.title(),
                article.description(),
                article.body(),
                article.tagList(),
                article.createdAt(),
                article.updatedAt(),
                article.favorited(),
                article.favoritesCount(),
                Author.from(user, isFollowing)
        );
    }
}
