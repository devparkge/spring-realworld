package github.devparkge.realworld.controller.article.model.response.wrapper;

import github.devparkge.realworld.controller.article.model.response.ArticleResponse;

public record ArticleWrapper(
        ArticleResponse article
) {
    public static ArticleWrapper create(ArticleResponse article) {
        return new ArticleWrapper(
                article
        );
    }
}
