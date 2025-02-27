package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.user.model.User;

public interface ArticleRepository extends ArticleReadRepository {
    Article save(Article article);

    void delete(Article article);

    void  favorite(Article article, User user);

    void unFavorite(Article article, User user);
}
