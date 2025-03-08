package github.devparkge.realworld.infrastructure.comment.repository;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.comment.repository.CommentReadRepository;
import github.devparkge.realworld.infrastructure.article.dao.ArticleDao;
import github.devparkge.realworld.infrastructure.comment.dao.CommentDao;
import github.devparkge.realworld.infrastructure.comment.model.CommentEntity;
import github.devparkge.realworld.infrastructure.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentReadRepositoryImpl implements CommentReadRepository {
    protected final CommentDao commentDao;
    protected final ArticleDao articleDao;
    protected final UserDao userDao;

    @Override
    public List<Comment> findByArticleSlug(Slug slug) {
        return commentDao.findByArticleSlug(slug).stream()
                .map(CommentEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Comment> findByCommentId(int commentId) {
        return Optional.ofNullable(commentDao.findById((long) commentId).get().toDomain());
    }
}
