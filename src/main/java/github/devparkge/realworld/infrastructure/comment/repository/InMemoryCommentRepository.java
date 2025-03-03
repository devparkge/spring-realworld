package github.devparkge.realworld.infrastructure.comment.repository;

import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryCommentRepository extends InMemoryCommentReadRepository implements CommentRepository {

    @Override
    public Comment save(Comment comment) {
        int id = (comment.id() == -1) ? idIncrement.getAndIncrement() : comment.id();
        comments.put(id, comment.updateId(id));
        return comments.get(id);
    }

    @Override
    public void delete(Comment comment) {
        comments.remove(comment.id());
    }
}
