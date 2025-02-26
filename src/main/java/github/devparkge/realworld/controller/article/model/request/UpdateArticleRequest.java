package github.devparkge.realworld.controller.article.model.request;

public record UpdateArticleRequest(
        String title,
        String description,
        String body
) {
}
