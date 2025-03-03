package github.devparkge.realworld.domain.comment.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.exception.InvalidCommentBelogsToArticleException;
import org.springframework.stereotype.Service;

@Service
public class CommentValidateBelogsToArticleService {
    public void validateBelogsToArticle(Comment comment, Article article) {
        if(!comment.article().equals(article)) throw new InvalidCommentBelogsToArticleException();
    }
}
