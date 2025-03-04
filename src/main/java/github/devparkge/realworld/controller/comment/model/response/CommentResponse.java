package github.devparkge.realworld.controller.comment.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.devparkge.realworld.controller.model.Author;
import github.devparkge.realworld.domain.comment.model.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        int id,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
                timezone = "UTC"
        )
        LocalDateTime createdAt,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
                timezone = "UTC"
        )
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
