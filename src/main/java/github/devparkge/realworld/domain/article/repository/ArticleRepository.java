package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;

public interface ArticleRepository extends ArticleReadRepository {
    Article save(Article article);

    void delete(Article article);
}
