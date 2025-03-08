package github.devparkge.realworld.infrastructure.article.repository;

import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Tag;
import github.devparkge.realworld.domain.article.repository.ArticleRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.infrastructure.article.dao.ArticleDao;
import github.devparkge.realworld.infrastructure.article.dao.ArticleFavoriteDao;
import github.devparkge.realworld.infrastructure.article.dao.TagDao;
import github.devparkge.realworld.infrastructure.article.model.ArticleEntity;
import github.devparkge.realworld.infrastructure.article.model.FavoriteEntity;
import github.devparkge.realworld.infrastructure.article.model.TagEntity;
import github.devparkge.realworld.infrastructure.user.dao.UserDao;
import github.devparkge.realworld.infrastructure.user.model.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Transactional
public class ArticleRepositoryImpl extends ArticleReadRepositoryImpl implements ArticleRepository {
    private final TagDao tagDao;

    public ArticleRepositoryImpl(ArticleDao articleDao, ArticleFavoriteDao articleFavoriteDao, UserDao userDao, TagDao tagDao) {
        super(articleDao, articleFavoriteDao, userDao);
        this.tagDao = tagDao;
    }

    @Override
    public Article save(Article article) {
        Optional<ArticleEntity> optionalArticle = articleDao.findById(article.uuid());
        UserEntity userEntity = userDao.findById(article.author().uuid()).get();
        if (optionalArticle.isPresent()) {
            ArticleEntity articleEntity = optionalArticle.get();
            articleEntity.update(article);
            return articleEntity.toDomain();
        }
        return articleDao.save(ArticleEntity.from(article,
                getTags(article),
                userEntity)).toDomain();
    }

    private List<TagEntity> getTags(Article article) {
        List<TagEntity> tags = tagDao.findAllById(article.tagList().stream().map(Tag::tagId).toList());

        List<String> dbTagNames = tags.stream()
                .map(TagEntity::getTagName)
                .toList();
        List<TagEntity> newTags = article.tagList().stream()
                .filter(tag -> !dbTagNames.contains(tag.tagName()))
                .map(TagEntity::from)
                .toList();

        return Stream.concat(
                tags.stream(),
                newTags.stream()
        ).toList();
    }

    @Override
    public void delete(Article article) {
        articleDao.deleteById(article.uuid());
    }

    @Override
    public void favorite(Article article, User user) {
        UserEntity userEntity = userDao.findById(user.uuid()).get();
        ArticleEntity articleEntity = articleDao.findById(article.uuid()).get();
        articleFavoriteDao.save(FavoriteEntity.from(userEntity, articleEntity));
    }

    @Override
    public void unFavorite(Article article, User user) {
        UserEntity userEntity = userDao.findById(user.uuid()).get();
        ArticleEntity articleEntity = articleDao.findById(article.uuid()).get();
        articleFavoriteDao.deleteByUserEntityAndArticleEntity(userEntity, articleEntity);
    }
}
