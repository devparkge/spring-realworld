package github.devparkge.realworld.controller.comment.model.response.wrapper;

import github.devparkge.realworld.controller.comment.model.response.CommentResponse;

import java.util.List;

public record CommentsWrapper(
        List<CommentResponse> comments
) {
    public static CommentsWrapper create(List<CommentResponse> comments) {
        return new CommentsWrapper(comments);
    }
}
