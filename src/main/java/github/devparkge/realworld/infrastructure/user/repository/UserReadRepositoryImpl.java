package github.devparkge.realworld.infrastructure.user.repository;

import github.devparkge.realworld.exception.UUIDNotFoundException;
import github.devparkge.realworld.infrastructure.user.dao.FollowDao;
import github.devparkge.realworld.infrastructure.user.dao.UserDao;
import github.devparkge.realworld.infrastructure.user.model.FollowEntity;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserReadRepository;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserReadRepositoryImpl implements UserReadRepository {
    protected final UserDao userDao;
    protected final FollowDao followDao;

    @Override
    public boolean isFollowing(UUID followerId, UUID followeeId) {
        UserEntity followerEntity = resolveUserEntity(followerId);
        UserEntity followeeEntity = resolveUserEntity(followeeId);
        return followDao.existsByFolloweeEntityAndFollowerEntity(followeeEntity, followerEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(
                userDao.findByEmail(email).get().toDomain()
        );
    }

    @Override
    public Optional<User> findByUUID(UUID uuid) {
        return Optional.of(
                userDao.findById(uuid).get().toDomain()
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.of(
                userDao.findByUsername(username).get().toDomain()
        );
    }

    protected UserEntity resolveUserEntity(UUID userId) {
        return userDao.findById(userId)
                .orElseThrow(() -> new UUIDNotFoundException(String.format("%s 존재하지 않는 아이디입니다.", userId)));
    }
}
