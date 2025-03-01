package github.devparkge.realworld.controller.article.model.response;

import github.devparkge.realworld.domain.article.model.Tag;

import java.util.List;

public record TagResponse(
        List<String> tags
) {
    public static TagResponse from(List<Tag> tags) {
        return new TagResponse(tags.stream().map(Tag::tagName).toList());
    }
}
