package github.devparkge.realworld.domain.comment.repository;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.comment.model.Comment;

import java.util.List;

public interface CommentReadRepository {
    List<Comment> findByArticleSlug(Slug slug);
}
