package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.infrastructure.article.model.ArticleFavorite;
import github.devparkge.realworld.infrastructure.article.model.ArticlePersistence;
import github.devparkge.realworld.infrastructure.article.model.Tag;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Predicate;

@Component
public class InMemoryArticleRepository extends InMemoryArticleReadRepository implements ArticleRepository {
    public InMemoryArticleRepository(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public Article save(Article article) {
        articles.put(article.uuid(), ArticlePersistence.from(article));
        article.tagList().stream()
                .filter(tag -> tags.stream()
                        .noneMatch(matchesTag(tag)))
                .forEach(tag -> tags.add(Tag.create(tag)));
        return article;
    }

    private static Predicate<Tag> matchesTag(String tag) {
        return tag1 -> tag.equals(tag1.tagName());
    }

    @Override
    public void delete(Article article) {
        articles.remove(article.uuid());
    }

    @Override
    public void favorite(Article article, User user) {
        favorites.add(
                new ArticleFavorite(
                        UUID.randomUUID(),
                        user.uuid(),
                        article.uuid()
                )
        );
    }

    @Override
    public void unFavorite(Article article, User user) {
        favorites.stream()
                .filter(articleFavorite -> articleFavorite.articleId().equals(article.uuid()) && articleFavorite.userId().equals(user.uuid()))
                .findFirst()
                .map(articleFavorite -> favorites.remove(articleFavorite));
    }
}
