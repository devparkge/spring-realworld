package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
}
