package github.devparkge.realworld.domain.user.repository;

import github.devparkge.realworld.infrastructure.user.repository.InMemoryUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class InMemoryUserRepositoryTest {
    @Test
    void follow() {
        var repository = new InMemoryUserRepository();

        UUID followeeId = UUID.randomUUID();
        UUID followerId = UUID.randomUUID();
        repository.follow(followerId, followeeId);

        var isFollow = repository.isFollowing(followerId, followeeId);
        Assertions.assertThat(isFollow).isTrue();
    }
    @Test
    void follow_false() {
        var repository = new InMemoryUserRepository();

        UUID followeeId = UUID.randomUUID();
        UUID followerId = UUID.randomUUID();
        repository.follow(followerId, followeeId);

        var isFollow = repository.isFollowing(UUID.randomUUID(), followeeId);
        Assertions.assertThat(isFollow).isFalse();
    }
    @Test
    void unFollow_true() {
        var repository = new InMemoryUserRepository();
        UUID followeeId = UUID.randomUUID();
        UUID followerId = UUID.randomUUID();
        repository.follow(followerId, followeeId);
        repository.unFollow(followerId, followeeId);

        var isFollow = repository.isFollowing(followerId, followeeId);
        Assertions.assertThat(isFollow).isFalse();
    }
    @Test
    void unFollow_false() {
        var repository = new InMemoryUserRepository();
        UUID followeeId = UUID.randomUUID();
        UUID followerId = UUID.randomUUID();
        repository.follow(followerId, followeeId);
        repository.unFollow(UUID.randomUUID(), followeeId);

        var isFollow = repository.isFollowing(followerId, followeeId);
        Assertions.assertThat(isFollow).isTrue();
    }
}
