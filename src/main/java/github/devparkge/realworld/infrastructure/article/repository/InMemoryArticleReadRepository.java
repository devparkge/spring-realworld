package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.ArticleReadRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.UsernameNotFoundException;
import github.devparkge.realworld.infrastructure.article.model.ArticleFavorite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class InMemoryArticleReadRepository implements ArticleReadRepository {
    protected List<Article> articles = new ArrayList<>();
    protected List<ArticleFavorite> favorites = new ArrayList<>();
    protected final UserRepository userRepository;

    @Override
    public List<Article> findByTagOrAuthor(String tagName, String author, String favorited, int limit, int offset) {
        User user = (favorited != null) ? userRepository.findByUsername(favorited).orElseThrow(() -> new UsernameNotFoundException(String.format("%s은 존재하지 않는 유저입니다.", favorited))) : null;
        List<ArticleFavorite> favoriteList = (favorited != null) ? favorites.stream().filter(articleFavorite -> articleFavorite.userId().equals(user.uuid())).toList() : null;
        return articles.stream()
                .filter(article -> tagName == null || article.tagList().stream().anyMatch(isMatchTag(tagName)))
                .toList().stream()
                .filter(article -> author == null || article.author().username().equals(author))
                .toList().stream()
                .filter(article -> {
                    if (favoriteList != null) {
                        favoriteList.stream().anyMatch(articleFavorite -> articleFavorite.articleId().equals(article.uuid()));
                        userRepository.updateUser(article.author());
                    }
                    userRepository.updateUser(article.author());
                    return true;
                }).skip(offset)
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
    public int getCountByArticleId(UUID articleId) {
        return favorites.stream()
                .filter(
                        articleFavorite ->
                                articleFavorite.articleId().equals(articleId)
                ).toList()
                .size();
    }

    private Predicate<? super String> isMatchTag(String tagName) {
        return tag -> tag.equals(tagName);
    }

    public void clear() {
        articles = new ArrayList<>();
        favorites = new ArrayList<>();
    }
}
