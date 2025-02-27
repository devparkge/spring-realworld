package github.devparkge.realworld.controller.article;

import github.devparkge.realworld.controller.article.model.response.ArticleResponse;
import github.devparkge.realworld.controller.article.model.response.wrapper.ArticleWrapper;
import github.devparkge.realworld.controller.article.model.response.wrapper.ArticlesWrapper;
import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.service.FavoriteArticleService;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.service.FollowService;
import github.devparkge.realworld.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.BiPredicate;

@Component
@RequiredArgsConstructor
public class ArticleResponseAssembler {
    private final GetUserService getUserService;
    private final FollowService followService;
    private final FavoriteArticleService favoriteArticleService;

    public ArticleWrapper assembleArticleResponse(Article article, UUID authUserUUID) {
        User author = getUserService.getByUUID(authUserUUID);
        int favoriteCount = favoriteArticleService.getFavoriteCount(article.uuid());
        boolean favorited = getContext(
                authUserUUID,
                article.uuid(),
                (userId, articleId) -> favoriteArticleService.getFavoritesArticleIds(userId).contains(articleId)
        );
        boolean isFollowing = getContext(
                authUserUUID,
                article.author().uuid(),
                (userId, articleUserId) -> followService.isFollowing(articleUserId, userId)
        );
        return ArticleWrapper.create(ArticleResponse.from(article, author, favorited, favoriteCount, isFollowing));
    }

    public ArticlesWrapper assembleArticlesResponse(List<Article> articles, UUID authUserUUID) {
        return ArticlesWrapper.create(
                articles.stream()
                        .map(article -> {
                                    User author = article.author();
                                    boolean favorited = getContext(
                                            authUserUUID,
                                            article.uuid(),
                                            (userId, articleId) -> favoriteArticleService.getFavoritesArticleIds(userId).contains(articleId)
                                    );
                                    boolean isFollowing = getContext(
                                            authUserUUID,
                                            article.author().uuid(),
                                            (userId, articleUserId) -> followService.isFollowing(articleUserId, userId)
                                    );
                                    int favoriteCount = favoriteArticleService.getFavoriteCount(article.uuid());
                                    return ArticleResponse.from(article, author, favorited, favoriteCount, isFollowing);
                                }
                        ).toList()
        );
    }

    public boolean getContext(UUID authUserUUID, UUID targetId, BiPredicate<UUID, UUID> condition) {
        return (authUserUUID != null) && condition.test(authUserUUID, targetId);
    }
}
