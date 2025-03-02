package github.devparkge.realworld.domain.comment.repository;

import github.devparkge.realworld.domain.comment.model.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
}
