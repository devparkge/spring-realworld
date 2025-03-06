package github.devparkge.realworld.infrastructure.user.dao;

import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDao extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(UUID userId);
    Optional<UserEntity> findByUsername(String username);
}
