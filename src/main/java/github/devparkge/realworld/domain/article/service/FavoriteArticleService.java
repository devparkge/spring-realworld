package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteArticleService {
    private final ArticleRepository articleRepository;

    public List<UUID> getFavoritesArticleIds(UUID userId) {
        return articleRepository.getFavoritesArticleIds(userId);
    }

    public int getFavoriteCount(UUID articleId) {
        return articleRepository.getCountByArticleId(articleId);
    }
}
