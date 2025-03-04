package github.devparkge.realworld.controller.article.api;

import github.devparkge.realworld.config.annotation.JsonRequest;
import github.devparkge.realworld.config.annotation.JwtAuthenticationOptional;
import github.devparkge.realworld.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.controller.article.ArticleResponseAssembler;
import github.devparkge.realworld.controller.article.model.request.CreateArticleRequest;
import github.devparkge.realworld.controller.article.model.request.GetArticlesRequest;
import github.devparkge.realworld.controller.article.model.request.GetFeedArticlesRequest;
import github.devparkge.realworld.controller.article.model.request.UpdateArticleRequest;
import github.devparkge.realworld.controller.article.model.response.wrapper.ArticleWrapper;
import github.devparkge.realworld.controller.article.model.response.wrapper.ArticlesWrapper;
import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.service.*;
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
    private final UpdateArticleService updateArticleService;
    private final DeleteArticleService deleteArticleService;
    private final FavoriteArticleService favoriteArticleService;

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

    @GetMapping("/{slug}")
    public ArticleWrapper getArticle(
            @PathVariable("slug") String slug
    ) {
        Article article = getArticlesService.getArticle(Slug.from(slug));
        return articleResponseAssembler.assembleArticleResponse(article, null);
    }

    @GetMapping("/feed")
    public ArticlesWrapper getFeedArticles(
            @JwtAuthenticationRequired UUID authUserUUID,
            @ModelAttribute GetFeedArticlesRequest getFeedArticlesRequest
    ) {
        List<Article> articles = getArticlesService.getFeedArticles(authUserUUID, getFeedArticlesRequest.limit(), getFeedArticlesRequest.offset());
        return articleResponseAssembler.assembleArticlesResponse(articles, authUserUUID);
    }

    @PutMapping("/{slug}")
    public ArticleWrapper updateArticle(
            @JwtAuthenticationRequired UUID authUserUUID,
            @PathVariable("slug") String slug,
            @JsonRequest("article") UpdateArticleRequest updateArticleRequest
    ) {
        Article article = updateArticleService.update(
                Slug.from(slug),
                updateArticleRequest.title(),
                updateArticleRequest.description(),
                updateArticleRequest.body()
        );
        return articleResponseAssembler.assembleArticleResponse(article, authUserUUID);
    }

    @DeleteMapping("/{slug}")
    public void deleteArticle(
            @JwtAuthenticationRequired UUID authUserUUID,
            @PathVariable("slug") String slug
    ) {
        deleteArticleService.deleteArticle(Slug.from(slug), authUserUUID);
    }

    @PostMapping("/{slug}/favorite")
    public ArticleWrapper favoriteArticle(
            @JwtAuthenticationRequired UUID authUserUUID,
            @PathVariable("slug") String slug
    ) {
        return articleResponseAssembler.assembleArticleResponse(
                favoriteArticleService.favorite(Slug.from(slug), authUserUUID),
                authUserUUID
        );
    }

    @DeleteMapping("/{slug}/favorite")
    public ArticleWrapper unFavoriteArticle(
            @JwtAuthenticationRequired UUID authUserUUID,
            @PathVariable("slug") String slug
    ) {
        return articleResponseAssembler.assembleArticleResponse(
                favoriteArticleService.unFavorite(Slug.from(slug), authUserUUID),
                authUserUUID
        );
    }
}
