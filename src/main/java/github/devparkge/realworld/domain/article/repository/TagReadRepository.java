package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Tag;

import java.util.List;

public interface TagReadRepository {
    List<Tag> findAll();
}
