package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository {
    Article saveArticle(UUID userId, String title, String description, String body, List<String> tagList);
}
