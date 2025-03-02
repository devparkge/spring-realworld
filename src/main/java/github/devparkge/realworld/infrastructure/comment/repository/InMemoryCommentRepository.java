package github.devparkge.realworld.infrastructure.comment.repository;

import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class InMemoryCommentRepository implements CommentRepository {
    private Map<Integer, Comment> comments = new HashMap<>();
    private AtomicInteger idIncrement = new AtomicInteger(1);

    @Override
    public Comment save(Comment comment) {
        int id = (comment.id() == -1) ? idIncrement.getAndIncrement() : comment.id();
        comments.put(id, comment.updateId(id));
        return comments.get(id);
    }

    public void clear() {
        comments = new HashMap<>();
        idIncrement = new AtomicInteger(1);
    }
}
