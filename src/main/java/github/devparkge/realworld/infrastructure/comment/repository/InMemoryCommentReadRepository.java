package github.devparkge.realworld.infrastructure.comment.repository;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.comment.repository.CommentReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class InMemoryCommentReadRepository implements CommentReadRepository {
    protected Map<Integer, Comment> comments = new HashMap<>();
    protected AtomicInteger idIncrement = new AtomicInteger(1);

    @Override
    public List<Comment> findByArticleSlug(Slug slug) {
        return findAll()
                .filter(comment -> comment.article().slug().equals(slug))
                .toList();
    }

    private Stream<Comment> findAll() {
        return comments.values().stream();
    }

    public void clear() {
        comments = new HashMap<>();
        idIncrement = new AtomicInteger(1);
    }
}
