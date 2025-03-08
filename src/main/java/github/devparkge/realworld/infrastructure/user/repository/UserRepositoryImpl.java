package github.devparkge.realworld.infrastructure.user.repository;

import github.devparkge.realworld.infrastructure.user.dao.FollowDao;
import github.devparkge.realworld.infrastructure.user.dao.UserDao;
import github.devparkge.realworld.infrastructure.user.model.FollowEntity;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepositoryImpl extends UserReadRepositoryImpl implements UserRepository {

    public UserRepositoryImpl(UserDao userDao, FollowDao followDao) {
        super(userDao, followDao);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(UserEntity.from(user)).toDomain();
    }

    @Override
    public void follow(UUID followeeId, UUID followerId) {
        UserEntity followeeEntity = resolveUserEntity(followeeId);
        UserEntity followerEntity = resolveUserEntity(followerId);
        followDao.save(FollowEntity.of(followeeEntity, followerEntity));
    }

    @Override
    public void unFollow(UUID followeeId, UUID followerId) {
        UserEntity followeeEntity = resolveUserEntity(followeeId);
        UserEntity followerEntity = resolveUserEntity(followerId);
        followDao.deleteByFolloweeEntityAndFollowerEntity(followeeEntity, followerEntity);
    }
}
