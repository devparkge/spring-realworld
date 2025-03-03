package github.devparkge.realworld.domain.comment.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.comment.repository.CommentRepository;
import github.devparkge.realworld.exception.SlugNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentOwnershipService commentOwnershipService;
    private final CommentValidateBelogsToArticleService commentValidateBelogsToArticleService;

    public void deleteComment(Slug slug, int id, UUID authUserUUID) {
        Comment comment = commentRepository.findByCommentId(id).orElseThrow(IllegalArgumentException::new);
        Article article = articleRepository.findBySlug(slug).orElseThrow(() -> new SlugNotFoundException(String.format("%s가 존재하지 않습니다.", slug.value())));

        commentOwnershipService.validateOwnership(comment, authUserUUID);
        commentValidateBelogsToArticleService.validateBelogsToArticle(comment, article);
        commentRepository.delete(comment);
    }
}
