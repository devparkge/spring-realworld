package github.devparkge.realworld.domain.comment.service;

import github.devparkge.realworld.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService {
    private final CommentRepository commentRepository;
}
