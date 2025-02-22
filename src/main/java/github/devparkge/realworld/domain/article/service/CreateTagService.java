package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Tag;
import github.devparkge.realworld.domain.article.repository.ArticleTagRepository;
import github.devparkge.realworld.domain.article.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CreateTagService {
    private final TagRepository tagRepository;
    private final ArticleTagRepository articleTagRepository;

    public void createTag(List<String> tagList, UUID articleId) {
        tagList.forEach(addTag(articleId));
    }

    private Consumer<String> addTag(UUID articleId) {
        return tagName ->{
                Tag tag = tagRepository.findByTagName(tagName).orElseGet(() -> tagRepository.saveTag(tagName));
                articleTagRepository.saveArticleTag(articleId, tag.tagId());
        };
    }
}
