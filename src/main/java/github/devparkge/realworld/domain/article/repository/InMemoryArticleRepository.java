package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.ArticleTag;
import github.devparkge.realworld.domain.article.model.Tag;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InMemoryArticleRepository implements ArticleRepository {
    private final ArticleTagRepository articleTagRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
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

    @Override
    public List<Article> findByTagOrAuthor(String tagName, String author, int limit, int offset) {
        if (tagName != null) return getFindByTag(tagName, limit, offset);
        if (author != null) return getFindByAuthor(author, limit, offset);
        return articles;
    }

    private List<Article> getFindByTag(String tagName, int limit, int offset) {
        return tagRepository.findByTagName(tagName)
                .map(getArticleId())
                .map(tags ->
                        articles.stream()
                                .filter(article -> tags.stream()
                                        .anyMatch(tag -> tag.articleId().equals(article.uuid())))
                                .skip(offset)
                                .limit(limit)
                                .collect(Collectors.toList())
                ).orElse(List.of());
    }

    private Function<Tag, List<ArticleTag>> getArticleId() {
        return tag -> articleTagRepository.findByTagId(tag.tagId());
    }

    private List<Article> getFindByAuthor(String author, int limit, int offset) {
        return userRepository.findByUsername(author)
                .map(user ->
                        articles.stream()
                                .filter(article -> article.userId().equals(user.uuid()))
                                .skip(offset)
                                .limit(limit)
                                .collect(Collectors.toList())
                ).orElse(List.of());
    }

    public void clear() {
        articles = new ArrayList<>();
    }
}
