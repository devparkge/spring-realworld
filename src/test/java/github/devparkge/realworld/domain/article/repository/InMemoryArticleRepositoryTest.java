package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;


class InMemoryArticleRepositoryTest {
    @Test
    void saveArticle() {
        var repository = new InMemoryArticleRepository();
        UUID userId = UUID.randomUUID();
        Article article = repository.saveArticle(
                userId,
                "Test",
                "test saveArticle",
                "test saveArticle unit test",
                List.of("unitTest", "test", "unit")
        );

        Assertions.assertThat(article.userId().equals(article.userId()));
        Assertions.assertThat(article.title().equals(article.title()));
        Assertions.assertThat(article.description().equals(article.description()));
        Assertions.assertThat(article.body().equals(article.body()));
        Assertions.assertThat(article.tagList().equals(article.tagList()));
    }
}