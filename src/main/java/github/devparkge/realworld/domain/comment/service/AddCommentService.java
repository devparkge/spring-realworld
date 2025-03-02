package github.devparkge.realworld.domain.comment.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.comment.repository.CommentRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.SlugNotFoundException;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddCommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public Comment addComment(Slug slug, UUID authUserUUID, String body) {
        User user = userRepository.findByUUID(authUserUUID).orElseThrow(() -> new UUIDNotFoundException(String.format("%s를 찾을 수 없습니다.", authUserUUID)));
        Article article = articleRepository.findBySlug(slug).orElseThrow(() -> new SlugNotFoundException(String.format("%s은 존재하지 않는 Slug입니다.", slug.value())));

        return commentRepository.save(
            Comment.create(
                    user,
                    article,
                    body
            )
        );
    }
}
