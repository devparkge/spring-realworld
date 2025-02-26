package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.exception.SlugNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateArticleService {
    private final ArticleRepository articleRepository;

    public Article update(Slug slug, String title, String description, String body) {
        Article updateArticle = articleRepository.findBySlug(slug)
                .map(article -> article.update(title, description, body))
                .orElseThrow(() -> new SlugNotFoundException(String.format("%s는 존재하지 않는 Slug입니다.", slug.value())));
        return articleRepository.save(updateArticle);
    }
}
