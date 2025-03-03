package github.devparkge.realworld.domain.comment.repository;

import github.devparkge.realworld.domain.comment.model.Comment;

public interface CommentRepository extends CommentReadRepository {
    Comment save(Comment comment);
}
