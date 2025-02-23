package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;


class InMemoryArticleRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveArticle() {
        var repository = new InMemoryArticleRepository(
                userRepository
        );
        UUID userId = UUID.randomUUID();
        Article article = repository.save(
                userId,
                "Test",
                "test saveArticle",
                "test saveArticle unit test",
                List.of("unitTest", "test", "unit")
        );
        Assertions.assertThat(article.author().uuid().equals(article.author().uuid()));
        Assertions.assertThat(article.title().equals(article.title()));
        Assertions.assertThat(article.description().equals(article.description()));
        Assertions.assertThat(article.body().equals(article.body()));
        Assertions.assertThat(article.tagList().equals(article.tagList()));
    }
}