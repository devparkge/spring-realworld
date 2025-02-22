package github.devparkge.realworld.controller.article.request;

import java.util.List;

public record CreateArticleRequest(
        String title,
        String description,
        String body,
        List<String> tagList
) {
}
