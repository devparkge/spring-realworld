package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.infrastructure.article.model.ArticlePersistence;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InMemoryArticleRepository extends InMemoryArticleReadRepository implements ArticleRepository {
    public InMemoryArticleRepository(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public Article save(Article article) {
        articles.put(article.uuid(), ArticlePersistence.from(article));
        return article;
    }

    @Override
    public void delete(Slug slug, User authenticatedUser) {
        findAll()
                .filter(article -> article.author().equals(authenticatedUser))
                .filter(article -> article.slug().value().equals(slug.value()))
                .forEach(article -> articles.remove(article.uuid()));
    }
}
