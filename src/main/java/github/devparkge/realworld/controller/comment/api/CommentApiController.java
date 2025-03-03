package github.devparkge.realworld.controller.comment.api;

import github.devparkge.realworld.config.annotation.JsonRequest;
import github.devparkge.realworld.config.annotation.JwtAuthenticationOptional;
import github.devparkge.realworld.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.controller.comment.CommentResponseAssembler;
import github.devparkge.realworld.controller.comment.model.request.AddCommentRequest;
import github.devparkge.realworld.controller.comment.model.response.wrapper.CommentWrapper;
import github.devparkge.realworld.controller.comment.model.response.wrapper.CommentsWrapper;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.comment.service.AddCommentService;
import github.devparkge.realworld.domain.comment.service.GetCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles/{slug}/comments")
public class CommentApiController {
    private final CommentResponseAssembler commentResponseAssembler;
    private final AddCommentService addCommentService;
    private final GetCommentsService getCommentsService;

    @PostMapping
    public CommentWrapper addComment(
            @JwtAuthenticationRequired UUID authUserUUID,
            @PathVariable("slug") String slug,
            @JsonRequest("comment") AddCommentRequest addCommentRequest
    ) {
        return commentResponseAssembler.assembleCommentResponse(
                addCommentService.addComment(
                        Slug.from(slug),
                        authUserUUID,
                        addCommentRequest.body()
                )
        );
    }

    @GetMapping
    public CommentsWrapper getComments(
            @JwtAuthenticationOptional UUID authUserUUID,
            @PathVariable("slug") String slug
    ) {
        return commentResponseAssembler.assembleCommentsResponse(
                getCommentsService.getComments(Slug.from(slug)),
                authUserUUID
        );
    }
}
