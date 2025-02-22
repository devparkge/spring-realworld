package github.devparkge.realworld.domain.article.repository;

import github.devparkge.realworld.domain.article.model.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryTagRepository implements TagRepository {
    public List<Tag> tags = new ArrayList<>();

    @Override
    public Tag saveTag(String tagName) {
        Tag tag = Tag.add(tagName);
        tags.add(tag);
        return tag;
    }

    @Override
    public Optional<Tag> findByTagName(String tagName) {
        return tags.stream()
                .filter(tag -> tag.tagName().equals(tagName))
                .findFirst();
    }
}
