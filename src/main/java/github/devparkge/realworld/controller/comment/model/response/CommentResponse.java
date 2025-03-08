package github.devparkge.realworld.controller.comment.model.response;

import github.devparkge.realworld.controller.model.Author;
import github.devparkge.realworld.domain.comment.model.Comment;

import java.time.Instant;

public record CommentResponse(
        int id,
        Instant createdAt,
        Instant updatedAt,
        String body,
        Author author
) {
    public static CommentResponse from(Comment comment, boolean isFollowing) {
        return new CommentResponse(
                comment.id(),
                comment.createdAt(),
                comment.updatedAt(),
                comment.body(),
                Author.from(comment.author(), isFollowing)
        );
    }
}
