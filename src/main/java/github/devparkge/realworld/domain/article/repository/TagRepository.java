package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Tag;

import java.util.Optional;

public interface TagRepository {
    Tag saveTag(String tagName);
    Optional<Tag> findByTagName(String tagName);
}
