package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Tag;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.repository.UserRepository;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public Article createArticle(UUID userId, String title, String description, String body, List<String> tagList) {
        Article article = articleRepository.save(
                Article.create(
                        userRepository.findByUUID(userId).orElseThrow(() -> new UUIDNotFoundException(String.format("%s를 찾을 수 없습니다.", userId))),
                        title,
                        description,
                        body,
                        tagList.stream().map(Tag::create).toList()
                )
        );
        return article;
    }
}
