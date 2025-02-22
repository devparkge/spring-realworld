package github.devparkge.realworld.domain.article.repository;


import github.devparkge.realworld.domain.article.model.ArticleTag;

import java.util.List;
import java.util.UUID;

public interface ArticleTagRepository {
    ArticleTag saveArticleTag(UUID articleId, UUID tagId);
    List<ArticleTag> findByTagId(UUID tagId);
}
