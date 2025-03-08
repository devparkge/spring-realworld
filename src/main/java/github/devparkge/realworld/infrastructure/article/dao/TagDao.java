package github.devparkge.realworld.infrastructure.article.dao;

import github.devparkge.realworld.infrastructure.article.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagDao extends JpaRepository<TagEntity, UUID> {
}
