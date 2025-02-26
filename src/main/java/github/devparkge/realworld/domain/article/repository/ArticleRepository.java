package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.user.model.User;

public interface ArticleRepository extends ArticleReadRepository {
    Article save(Article article);
    void delete(Slug slug, User authenticatedUser);
}
