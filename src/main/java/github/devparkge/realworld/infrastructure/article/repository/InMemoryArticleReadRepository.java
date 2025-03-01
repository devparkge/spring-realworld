package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleReadRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.infrastructure.article.model.ArticleFavorite;
import github.devparkge.realworld.infrastructure.article.model.ArticlePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class InMemoryArticleReadRepository implements ArticleReadRepository {
    protected Map<UUID, ArticlePersistence> articles = new HashMap<>();
    protected List<ArticleFavorite> favorites = new ArrayList<>();
    protected final UserRepository userRepository;
    protected final InMemoryTagReadRepository tagReadRepository;

    @Override
    public List<Article> findByTagAndAuthorAndFavorited(String tagName, String author, String favorited, int limit, int offset) {
        return findAll()
                .filter(article -> equalTagName(tagName, article))
                .filter(article -> equalAuthorName(author, article))
                .filter(article -> equalFavorited(favorited, article))
                .skip(offset)
                .limit(limit)
                .toList();
    }

    @Override
    public List<UUID> getFavoritesArticleIds(UUID userId) {
        return favorites.stream()
                .filter(
                        articleFavorite ->
                                articleFavorite.userId().equals(userId)
                ).map(ArticleFavorite::articleId)
                .toList();
    }

    @Override
    public Optional<Article> findBySlug(Slug slug) {
        return findAll()
                .filter(article -> article.slug().equals(slug))
                .findFirst();
    }

    @Override
    public int getCountByArticleId(UUID articleId) {
        return favorites.stream()
                .filter(
                        articleFavorite ->
                                articleFavorite.articleId().equals(articleId)
                ).toList()
                .size();
    }

    private boolean equalFavorited(String favorited, Article article) {
        if (favorited == null) {
            return true;
        }
        Optional<User> maybeFavoritedUser = userRepository.findByUsername(favorited);
        if (maybeFavoritedUser.isEmpty()) {
            return true;
        }

        User favoritedUser = maybeFavoritedUser.get();
        List<UUID> favoritedArticleIds = favorites.stream()
                .filter(favorite -> favorite.userId().equals(favoritedUser.uuid()))
                .map(ArticleFavorite::articleId)
                .toList();
        return favoritedArticleIds.contains(article.uuid());
    }

    protected Stream<Article> findAll() {
        return articles.values().stream()
                .map(this::toDomain);
    }

    private static boolean equalAuthorName(String author, Article article) {
        if (author == null) return true;
        return article.author().username().equals(author);
    }

    private static boolean equalTagName(String tagName, Article article) {
        if (tagName == null) return true;
        return article.tagList().contains(tagName);
    }

    private Article toDomain(ArticlePersistence persistence) {
        User author = userRepository.findByUUID(persistence.authorId())
                .orElseThrow();
        return persistence.toDomain(author);
    }

    public void clear() {
        articles = new HashMap<>();
        favorites = new ArrayList<>();
    }
}

