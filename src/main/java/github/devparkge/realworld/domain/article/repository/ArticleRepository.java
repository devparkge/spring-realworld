package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;

import java.util.List;

public interface ArticleRepository {
    Article save(Article article);
    List<Article> findByTagOrAuthor(String tagName, String author, int limit, int offset);
}
