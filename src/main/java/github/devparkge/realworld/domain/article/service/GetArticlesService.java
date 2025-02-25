package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetArticlesService {
    private final ArticleRepository articleRepository;

    public List<Article> getArticles(String tag, String author, String favorited, int limit, int offset) {
        return articleRepository.findByTagOrAuthor(tag, author, favorited, limit, offset);
    }
}
