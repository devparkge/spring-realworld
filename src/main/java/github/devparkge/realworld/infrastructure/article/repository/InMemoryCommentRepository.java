package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Comment;
import github.devparkge.realworld.domain.article.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InMemoryCommentRepository implements CommentRepository {
    private List<Comment> comments = new ArrayList<>();

    @Override
    public Comment save(Comment comment) {
        comments.add(comment);
        return comment;
    }
}
