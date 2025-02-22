package github.devparkge.realworld.domain.article.repository;


import github.devparkge.realworld.domain.article.model.ArticleTag;

import java.util.UUID;

public interface ArticleTagRepository {
    ArticleTag saveArticleTag(UUID articleId, UUID tagId);
}
