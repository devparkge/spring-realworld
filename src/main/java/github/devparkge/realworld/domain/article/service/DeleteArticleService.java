package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public void deleteArticle(Slug slug, UUID authUserId) {
        User authenticatedUser = userRepository.findByUUID(authUserId).orElseThrow(() -> new UUIDNotFoundException(String.format("%s은 존재하지 않습니다.", authUserId)));
        articleRepository.delete(slug, authenticatedUser);
    }
}
