package github.devparkge.realworld.domain.user.repository;

import github.devparkge.realworld.infrastructure.user.repository.InMemoryUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class InMemoryUserRepositoryTest {
    @Test
    void follow() {
        var repository = new InMemoryUserRepository();

        UUID uuid = UUID.randomUUID();
        repository.follow("test", uuid);

        var isFollow = repository.isFollowing("test", uuid);
        Assertions.assertThat(isFollow).isTrue();
    }
    @Test
    void follow_false() {
        var repository = new InMemoryUserRepository();

        UUID uuid = UUID.randomUUID();
        repository.follow("test", uuid);

        var isFollow = repository.isFollowing("test1", uuid);
        Assertions.assertThat(isFollow).isFalse();
    }
    @Test
    void unFollow_true() {
        var repository = new InMemoryUserRepository();
        UUID uuid = UUID.randomUUID();
        repository.follow("test", uuid);
        repository.unFollow("test", uuid);

        var isFollow = repository.isFollowing("test", uuid);
        Assertions.assertThat(isFollow).isFalse();
    }
    @Test
    void unFollow_false() {
        var repository = new InMemoryUserRepository();
        UUID uuid = UUID.randomUUID();
        repository.follow("test", uuid);
        repository.unFollow("test1", uuid);

        var isFollow = repository.isFollowing("test", uuid);
        Assertions.assertThat(isFollow).isTrue();
    }
}
