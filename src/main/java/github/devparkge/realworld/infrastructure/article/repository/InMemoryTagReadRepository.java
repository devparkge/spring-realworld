package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Tag;
import github.devparkge.realworld.domain.article.repository.TagReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class InMemoryTagReadRepository implements TagReadRepository {
    protected List<Tag> tags = new ArrayList<>();

    @Override
    public List<Tag> findAll() {
        return tags;
    }

    public void save(Article article) {
        article.tagList().stream()
                .filter(tag -> tags.stream()
                        .noneMatch(matchesTag(tag)))
                .forEach(tag -> tags.add(Tag.create(tag)));
    }

    private Predicate<Tag> matchesTag(String tag) {
        return tag1 -> tag.equals(tag1.tagName());
    }

    public void clear() {
        tags = new ArrayList<>();
    }
}
