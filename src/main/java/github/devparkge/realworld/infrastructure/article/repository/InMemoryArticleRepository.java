package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if(tagName != null && author != null) {
            return getFindByAuthor(articles, author)
                    .map(articleList -> getFindByTag(articleList, tagName).orElse(List.of()))
                    .orElse(List.of()).stream()
                    .skip(offset)
                    .limit(limit)
                    .toList();
        }
        if(author != null) return getFindByAuthor(articles, author).orElse(List.of()).stream().skip(offset).limit(limit).toList();
        if(tagName != null) return getFindByTag(articles, tagName).orElse(List.of()).stream().skip(offset).limit(limit).toList();

        return articles.stream().skip(offset).limit(limit).toList();
    }

    private Optional<List<Article>> getFindByTag(List<Article> articleList, String tagName) {
        return Optional.of(
                articleList.stream()
                        .filter(article ->
                                article.tagList().stream()
                                        .anyMatch(tag -> {
                                            boolean matches = tag.equals(tagName);
                                            if(matches) userRepository.updateUser(article.author());
                                            return matches;
                                        })
                        ).toList());
    }

    private Optional<List<Article>> getFindByAuthor(List<Article> articleList, String author) {
        return Optional.of(userRepository.findByUsername(author)
                .map(user ->
                        articleList.stream()
                                .filter(article -> {
                                    boolean matches = article.author().uuid().equals(user.uuid());
                                    if(matches) userRepository.updateUser(article.author());
                                    return matches;
                                })
                                .toList()
                ).orElse(List.of()));
    }

    public void clear() {
        articles = new ArrayList<>();
    }
}
