package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleReadRepository {
    List<Article> findByTagAndAuthorAndFavorited(String tagName, String author, String favorited, int limit, int offset);
    List<Article> findFeedArticle(UUID userId, int limit, int offset);
    List<UUID> getFavoritesArticleIds(UUID userId);
    Optional<Article> findBySlug(Slug slug);
    int getCountByArticleId(UUID articleId);
}
