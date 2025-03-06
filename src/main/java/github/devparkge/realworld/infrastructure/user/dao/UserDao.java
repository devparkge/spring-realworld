package github.devparkge.realworld.infrastructure.user.dao;

import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDao extends JpaRepository<UserEntity, UUID> {
}
