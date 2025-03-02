package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InMemoryCommentRepository implements CommentRepository {
}
