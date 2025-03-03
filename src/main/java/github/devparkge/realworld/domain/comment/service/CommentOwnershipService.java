package github.devparkge.realworld.domain.comment.service;

import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.exception.InvalidCommentOwnershipException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentOwnershipService {
    public void validateOwnership(Comment comment, UUID author) {
        if(!comment.author().uuid().equals(author)) throw new InvalidCommentOwnershipException();
    }
}
