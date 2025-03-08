package github.devparkge.realworld.infrastructure.comment.repository;

import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.comment.repository.CommentRepository;
import github.devparkge.realworld.infrastructure.article.dao.ArticleDao;
import github.devparkge.realworld.infrastructure.article.model.ArticleEntity;
import github.devparkge.realworld.infrastructure.comment.dao.CommentDao;
import github.devparkge.realworld.infrastructure.comment.model.CommentEntity;
import github.devparkge.realworld.infrastructure.user.dao.UserDao;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentRepositoryImpl extends CommentReadRepositoryImpl implements CommentRepository {
    public CommentRepositoryImpl(CommentDao commentDao, ArticleDao articleDao, UserDao userDao) {
        super(commentDao, articleDao, userDao);
    }

    @Override
    public Comment save(Comment comment) {
        ArticleEntity articleEntity = articleDao.findById(comment.article().uuid()).get();
        UserEntity authorEntity = userDao.findById(comment.author().uuid()).get();
        return commentDao.save(CommentEntity.from(comment, authorEntity, articleEntity)).toDomain();
    }

    @Override
    public void delete(Comment comment) {
        commentDao.deleteById((long) comment.id());
    }
}
