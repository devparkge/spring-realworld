package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateArticleService {
    private final ArticleRepository articleRepository;

    public Article createArticle(UUID userId, String title, String description, String body, List<String> tagList) {
        return articleRepository.saveArticle(
                userId,
                title,
                description,
                body,
                tagList
        );
    }
}
