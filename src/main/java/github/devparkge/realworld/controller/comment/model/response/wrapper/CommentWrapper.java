package github.devparkge.realworld.controller.comment.model.response.wrapper;

import github.devparkge.realworld.controller.comment.model.response.CommentResponse;

public record CommentWrapper(
        CommentResponse comment
) {
    public static CommentWrapper create(CommentResponse comment) {
        return new CommentWrapper(comment);
    }
}
