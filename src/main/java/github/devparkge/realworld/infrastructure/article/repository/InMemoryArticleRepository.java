package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.infrastructure.article.model.ArticlePersistence;
import org.springframework.stereotype.Component;

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
}
