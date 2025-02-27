package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.NotArticleAuthorException;
import github.devparkge.realworld.exception.SlugNotFoundException;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public void deleteArticle(Slug slug, UUID authUserUUID) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(() -> new SlugNotFoundException(String.format("%s은 존재하지 않습니다.", slug.value())));
        User authenticatedUser = userRepository.findByUUID(authUserUUID).orElseThrow(() -> new UUIDNotFoundException(String.format("%s은 존재하지 않습니다.", authUserUUID)));
        if(!article.author().uuid().equals(authenticatedUser.uuid())) {
            throw new NotArticleAuthorException(String.format("%s은 %s와 일치하는 author가 아닙니다.", authenticatedUser.uuid(), article.author().uuid()));
        }
        articleRepository.delete(article);
    }
}
