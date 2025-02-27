package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.SlugNotFoundException;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public List<UUID> getFavoritesArticleIds(UUID userId) {
        return articleRepository.getFavoritesArticleIds(userId);
    }

    public int getFavoriteCount(UUID articleId) {
        return articleRepository.getCountByArticleId(articleId);
    }

    public Article favorite(Slug slug, UUID authUserUUID) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(() -> new SlugNotFoundException(String.format("%s 존재하지 않는 slug 입니다.", slug.value())));
        User user = userRepository.findByUUID(authUserUUID).orElseThrow(() -> new UUIDNotFoundException(String.format("%s은 존재하지 않는 아이디 입니다.", authUserUUID)));
        articleRepository.favorite(article, user);
        return article;
    }
}
