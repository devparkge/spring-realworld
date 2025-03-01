package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import github.devparkge.realworld.infrastructure.article.repository.InMemoryArticleRepository;
import github.devparkge.realworld.infrastructure.article.repository.InMemoryTagReadRepository;
import github.devparkge.realworld.infrastructure.user.repository.InMemoryUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class InMemoryArticleRepositoryTest {
    @Test
    void saveArticle() {
        var userRepository = new InMemoryUserRepository();
        var tagReadRepository = new InMemoryTagReadRepository();
        var repository = new InMemoryArticleRepository(userRepository, tagReadRepository);
        User user = userRepository.saveUser(
                User.signUp(
                        UUID.randomUUID(),
                        "parkge",
                        "1234",
                        "parkge@gmail.com"
                )
        );
        Article article = repository.save(
                Article.create(
                        userRepository.findByUUID(user.uuid()).orElseThrow(() -> new UUIDNotFoundException(String.format("%s를 찾을 수 없습니다.", user.uuid()))),
                        "Test",
                        "test saveArticle",
                        "test saveArticle unit test",
                        List.of("unitTest", "test", "unit")
                )
        );
        Assertions.assertThat(article.author().uuid().equals(article.author().uuid()));
        Assertions.assertThat(article.title().equals(article.title()));
        Assertions.assertThat(article.description().equals(article.description()));
        Assertions.assertThat(article.body().equals(article.body()));
        Assertions.assertThat(article.tagList().equals(article.tagList()));
    }
}