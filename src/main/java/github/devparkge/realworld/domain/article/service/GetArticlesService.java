package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.exception.SlugNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetArticlesService {
    private final ArticleRepository articleRepository;

    public List<Article> getArticles(String tag, String author, String favorited, int limit, int offset) {
        return articleRepository.findByTagAndAuthorAndFavorited(tag, author, favorited, limit, offset);
    }

    public Article getArticle(Slug slug) {
        return articleRepository.findBySlug(slug).orElseThrow(() -> new SlugNotFoundException(String.format("%s은 존재하지 않는 slug입니다.", slug.value())));
    }

    public List<Article> getFeedArticles(UUID userId, int limit, int offset) {
        return articleRepository.findFeedArticle(userId, limit, offset);
    }
}
