package github.devparkge.realworld.controller.comment;

import github.devparkge.realworld.controller.comment.model.response.CommentResponse;
import github.devparkge.realworld.controller.comment.model.response.wrapper.CommentWrapper;
import github.devparkge.realworld.controller.comment.model.response.wrapper.CommentsWrapper;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class CommentResponseAssembler {
    private final UserRepository userRepository;

    public CommentWrapper assembleCommentResponse(Comment comment) {
        return CommentWrapper.create(
                CommentResponse.from(
                        comment,
                        userRepository.isFollowing(comment.article().author().uuid(), comment.author().uuid())
                )
        );
    }

    public CommentsWrapper assembleCommentsResponse(List<Comment> comments, UUID authUserUUID) {
        return CommentsWrapper.create(
                comments.stream()
                        .map(comment -> CommentResponse.from(
                                comment,
                                authUserUUID != null && userRepository.isFollowing(comment.author().uuid(), authUserUUID)
                        )).toList()
        );
    }
}
