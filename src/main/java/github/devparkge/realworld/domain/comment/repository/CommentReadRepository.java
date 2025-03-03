package github.devparkge.realworld.domain.comment.repository;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.comment.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentReadRepository {
    List<Comment> findByArticleSlug(Slug slug);
    Optional<Comment> findByCommentId(int commentId);
}
