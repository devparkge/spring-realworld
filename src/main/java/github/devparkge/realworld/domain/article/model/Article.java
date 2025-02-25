package github.devparkge.realworld.domain.article.model;

import github.devparkge.realworld.domain.user.model.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record Article(
        UUID uuid,
        User author,
        Slug slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static Article create(User author, String title, String description, String body, List<String> tagList) {
        return Article.builder()
                .uuid(UUID.randomUUID())
                .author(author)
                .title(title)
                .slug(Slug.from(title))
                .description(description)
                .body(body)
                .tagList(tagList)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
