package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Tag;
import github.devparkge.realworld.domain.article.repository.TagReadRepository;
import github.devparkge.realworld.infrastructure.article.dao.TagDao;
import github.devparkge.realworld.infrastructure.article.model.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagReadRepositoryImpl implements TagReadRepository {
    private final TagDao tagDao;

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll().stream().map(TagEntity::toDomain).toList();
    }
}
