package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.config.annotation.JsonRequest;
import github.devparkge.realworld.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.controller.request.CreateArticleRequest;
import github.devparkge.realworld.controller.response.ArticleResponse;
import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.service.CreateArticleService;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.service.FollowService;
import github.devparkge.realworld.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticlesApiController {
    private final GetUserService getUserService;
    private final FollowService followService;
    private final CreateArticleService createArticleService;

    @PostMapping()
    public ArticleResponse createArticle(
            @JwtAuthenticationRequired UUID authUserUUID,
            @JsonRequest("article") CreateArticleRequest createArticleRequest
    ) {
        Article article = createArticleService.createArticle(
                authUserUUID,
                createArticleRequest.title(),
                createArticleRequest.description(),
                createArticleRequest.body(),
                createArticleRequest.tagList()
        );
        User user = getUserService.getByUUID(authUserUUID);
        boolean isFollowing = followService.isFollowing(user.username(),authUserUUID);
        return ArticleResponse.from(article, user, isFollowing);
    }
}
