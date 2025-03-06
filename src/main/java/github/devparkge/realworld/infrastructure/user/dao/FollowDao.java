package github.devparkge.realworld.infrastructure.user.dao;

import github.devparkge.realworld.infrastructure.user.model.FollowEntity;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowDao extends JpaRepository<FollowEntity, Long> {
    boolean existsByFolloweeEntityAndFollowerEntity(UserEntity followeeEntity, UserEntity followerEntity);
    void deleteByFolloweeEntityAndFollowerEntity(UserEntity followeeEntity, UserEntity followerEntity);
}
