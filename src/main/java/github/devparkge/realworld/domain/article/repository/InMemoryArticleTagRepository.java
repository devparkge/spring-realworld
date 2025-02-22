package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.ArticleTag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InMemoryArticleTagRepository implements ArticleTagRepository{
    public List<ArticleTag> articleTags = new ArrayList<>();

    @Override
    public ArticleTag saveArticleTag(UUID articleId, UUID tagId) {
        ArticleTag articleTag = ArticleTag.add(
                articleId,
                tagId
        );
        articleTags.add(articleTag);
        return articleTag;
    }
}
