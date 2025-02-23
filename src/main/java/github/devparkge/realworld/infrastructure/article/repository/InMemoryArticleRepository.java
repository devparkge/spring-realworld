package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InMemoryArticleRepository implements ArticleRepository {
    private final UserRepository userRepository;
    public List<Article> articles = new ArrayList<>();

    @Override
    public Article save(Article article) {
        articles.add(article);
        return article;
    }

    @Override
    public List<Article> findByTagOrAuthor(String tagName, String author, int limit, int offset) {
        if (tagName != null) return getFindByTag(tagName, limit, offset);
        if (author != null) return getFindByAuthor(author, limit, offset);
        return articles;
    }

    private List<Article> getFindByTag(String tagName, int limit, int offset) {
        return articles.stream()
                .filter(article ->
                        article.tagList().stream()
                                .anyMatch(tag -> tag.equals(tagName))
                ).skip(offset)
                .limit(limit)
                .toList();
    }

    private List<Article> getFindByAuthor(String author, int limit, int offset) {
        return userRepository.findByUsername(author)
                .map(user ->
                        articles.stream()
                                .filter(article -> article.author().uuid().equals(user.uuid()))
                                .skip(offset)
                                .limit(limit)
                                .toList()
                ).orElse(List.of());
    }

    public void clear() {
        articles = new ArrayList<>();
    }
}
