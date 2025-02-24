package github.devparkge.realworld.controller.article.api;

import github.devparkge.realworld.config.annotation.JsonRequest;
import github.devparkge.realworld.config.annotation.JwtAuthenticationOptional;
import github.devparkge.realworld.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.controller.article.ArticleResponseAssembler;
import github.devparkge.realworld.controller.article.model.request.CreateArticleRequest;
import github.devparkge.realworld.controller.article.model.request.GetArticlesRequest;
import github.devparkge.realworld.controller.article.model.response.wrapper.ArticleWrapper;
import github.devparkge.realworld.controller.article.model.response.wrapper.ArticlesWrapper;
import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.service.CreateArticleService;
import github.devparkge.realworld.domain.article.service.GetArticlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticlesApiController {
    private final CreateArticleService createArticleService;
    private final GetArticlesService getArticlesService;
    private final ArticleResponseAssembler articleResponseAssembler;

    @PostMapping
    public ArticleWrapper createArticle(
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
        return articleResponseAssembler.assembleArticleResponse(article, authUserUUID);
    }

    @GetMapping
    public ArticlesWrapper getArticles(
            @JwtAuthenticationOptional UUID authUserUUID,
            @ModelAttribute GetArticlesRequest getArticlesRequest
    ) {
        List<Article> articles = getArticlesService.getArticles(
                getArticlesRequest.tag(),
                getArticlesRequest.author(),
                getArticlesRequest.favorited(),
                getArticlesRequest.limit(),
                getArticlesRequest.offset()
        );
        return articleResponseAssembler.assembleArticlesResponse(articles, authUserUUID);
    }
}
