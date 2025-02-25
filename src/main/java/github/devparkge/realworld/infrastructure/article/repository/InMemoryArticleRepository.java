package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryArticleRepository extends InMemoryArticleReadRepository implements ArticleRepository {

    public InMemoryArticleRepository(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public Article save(Article article) {
        articles.add(article);
        return article;
    }
}
