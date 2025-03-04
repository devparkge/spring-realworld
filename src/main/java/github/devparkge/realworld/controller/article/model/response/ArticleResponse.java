package github.devparkge.realworld.controller.article.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.devparkge.realworld.controller.model.Author;
import github.devparkge.realworld.domain.article.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponse(
        String slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
                timezone = "UTC"
        )
        LocalDateTime createdAt,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
                timezone = "UTC"
        )
        LocalDateTime updatedAt,
        boolean favorited,
        int favoritesCount,
        Author author
) {
    public static ArticleResponse from(Article article, boolean favorited, int favoritesCount, boolean isFollowing) {
        List<String> sortedList = article.tagList().stream().sorted().toList();
        return new ArticleResponse(
                article.slug().value(),
                article.title(),
                article.description(),
                article.body(),
                sortedList,
                article.createdAt(),
                article.updatedAt(),
                favorited,
                favoritesCount,
                Author.from(article.author(), isFollowing)
        );
    }
}
