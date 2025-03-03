package github.devparkge.realworld.domain.comment.service;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommentsService {
    private final CommentRepository commentRepository;

    public List<Comment> getComments(Slug slug) {
        return commentRepository.findByArticleSlug(slug);
    }
}
