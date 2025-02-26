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

    public Article update(String title, String description, String body) {
        return Article.builder()
                .uuid(uuid())
                .author(author())
                .tagList(tagList())
                .createdAt(createdAt())
                .updatedAt(LocalDateTime.now())
                .slug((title != null) ? Slug.from(title) : slug())
                .title((title != null) ? title : title())
                .description((description != null) ? description : description())
                .body((body != null) ? body : body())
                .build();
    }
}
