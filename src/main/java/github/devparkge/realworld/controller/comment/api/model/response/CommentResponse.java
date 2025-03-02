package github.devparkge.realworld.controller.comment.api.model.response;

import github.devparkge.realworld.controller.model.Author;
import github.devparkge.realworld.domain.comment.model.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        int id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
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
