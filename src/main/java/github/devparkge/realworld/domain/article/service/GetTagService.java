package github.devparkge.realworld.domain.article.service;

import github.devparkge.realworld.domain.article.model.Tag;
import github.devparkge.realworld.domain.article.repository.TagReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTagService {
    private final TagReadRepository tagReadRepository;
    public List<Tag> getTags() {
        return tagReadRepository.findAll();
    }
}
