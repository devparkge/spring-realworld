package github.devparkge.realworld.domain.user.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class InMemoryFollowerRepositoryTest {
    @Test
    void follow() {
        var repository = new InMemoryFollowerRepository();

        UUID uuid = UUID.randomUUID();
        repository.follow("test", uuid);

        var isFollow = repository.isFollow("test", uuid);
        Assertions.assertThat(isFollow).isTrue();
    }
    @Test
    void follow_false() {
        var repository = new InMemoryFollowerRepository();

        UUID uuid = UUID.randomUUID();
        repository.follow("test", uuid);

        var isFollow = repository.isFollow("test1", uuid);
        Assertions.assertThat(isFollow).isFalse();
    }
    @Test
    void unFollow_true() {
        var repository = new InMemoryFollowerRepository();
        UUID uuid = UUID.randomUUID();
        repository.follow("test", uuid);
        repository.unFollow("test", uuid);

        var isFollow = repository.isFollow("test", uuid);
        Assertions.assertThat(isFollow).isFalse();
    }
    @Test
    void unFollow_false() {
        var repository = new InMemoryFollowerRepository();
        UUID uuid = UUID.randomUUID();
        repository.follow("test", uuid);
        repository.unFollow("test1", uuid);

        var isFollow = repository.isFollow("test", uuid);
        Assertions.assertThat(isFollow).isTrue();
    }
}
