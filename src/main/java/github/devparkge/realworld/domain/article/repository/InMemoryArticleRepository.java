package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InMemoryArticleRepository implements ArticleRepository {
    public List<Article> articles = new ArrayList<>();

    @Override
    public Article saveArticle(UUID userId, String title, String description, String body, List<String> tagList) {
        Article article = Article.addArticle(
                userId,
                title,
                description,
                body,
                tagList
        );
        articles.add(article);
        return article;
    }

    public void clear() {
        articles = new ArrayList<>();
    }
}
