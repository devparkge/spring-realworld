package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.domain.repository.InMemoryFollowerRepository;
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
}
