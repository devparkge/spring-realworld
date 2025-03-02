package github.devparkge.realworld.controller.comment;

import github.devparkge.realworld.controller.comment.model.response.CommentResponse;
import github.devparkge.realworld.controller.comment.model.response.wrapper.CommentWrapper;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


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
}
