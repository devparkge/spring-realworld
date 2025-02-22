package github.devparkge.realworld.controller.article.model.response.wrapper;

import github.devparkge.realworld.controller.article.model.response.ArticleResponse;

import java.util.List;

public record ArticlesWrapper(
        List<ArticleResponse> articles,
        int articlesCount
) {
    public static ArticlesWrapper create(List<ArticleResponse> articles) {
        return new ArticlesWrapper(
                articles,
                articles.size()
        );
    }
}
