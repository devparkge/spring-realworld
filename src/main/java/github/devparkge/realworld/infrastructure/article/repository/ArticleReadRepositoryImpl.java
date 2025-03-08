package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleReadRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.infrastructure.article.dao.ArticleDao;
import github.devparkge.realworld.infrastructure.article.dao.ArticleFavoriteDao;
import github.devparkge.realworld.infrastructure.article.model.ArticleEntity;
import github.devparkge.realworld.infrastructure.user.dao.UserDao;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ArticleReadRepositoryImpl implements ArticleReadRepository {
    protected final ArticleDao articleDao;
    protected final ArticleFavoriteDao articleFavoriteDao;
    protected final UserDao userDao;

    @Override
    public List<Article> findByTagAndAuthorAndFavorited(String tagName, String author, String favorited, int limit, int offset) {
        return articleDao.findAll().stream()
                .filter(articleEntity -> equalTagName(tagName, articleEntity))
                .filter(articleEntity -> equalAuthorName(author, articleEntity))
                .filter(articleEntity -> equalFavorited(favorited, articleEntity))
                .map(ArticleEntity::toDomain)
                .skip(offset)
                .limit(limit)
                .toList();
    }

    @Override
    public List<Article> findFeedArticle(UUID userId, int limit, int offset) {
        return articleDao.findFeedArticles(userId).stream()
                .map(ArticleEntity::toDomain)
                .skip(offset)
                .limit(limit)
                .toList();
    }

    @Override
    public List<UUID> getFavoritesArticleIds(UUID userId) {
        return articleDao.getFavoriteArticles(userId);
    }

    @Override
    public Optional<Article> findBySlug(Slug slug) {
        return articleDao.findBySlug(slug).map(ArticleEntity::toDomain);
    }

    @Override
    public int getCountByArticleId(UUID articleId) {
        return articleFavoriteDao.conutByArticleEntity(articleId);
    }

    private boolean equalFavorited(String favoritedUsername, ArticleEntity articleEntity) {
        if (favoritedUsername == null) {
            return true;
        }
        Optional<UserEntity> maybeFavoritedUser = userDao.findByUsername(favoritedUsername);
        if (maybeFavoritedUser.isEmpty()) {
            return true;
        }

        User favoritedUser = maybeFavoritedUser.get().toDomain();
        List<UUID> favoritedArticleIds = articleDao.getFavoriteArticles(favoritedUser.uuid());
        return favoritedArticleIds.contains(articleEntity.toDomain().uuid());
    }

    private static boolean equalAuthorName(String author, ArticleEntity articleEntity) {
        if (author == null) return true;
        return articleEntity.toDomain().author().username().equals(author);
    }

    private static boolean equalTagName(String tagName, ArticleEntity articleEntity) {
        if (tagName == null) return true;
        return articleEntity.toDomain().tagList().stream()
                .anyMatch(tag -> tag.tagName().equals(tagName));
    }
}

