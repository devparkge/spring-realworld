package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleReadRepository {
    List<Article> findByTagOrAuthor(String tagName, String author, String favorited, int limit, int offset);
    List<UUID> getFavoritesArticleIds(UUID userId);
    int getCountByArticleId(UUID articleId);
}
