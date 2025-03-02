package github.devparkge.realworld.controller.comment.api;

import github.devparkge.realworld.config.annotation.JsonRequest;
import github.devparkge.realworld.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.controller.comment.CommentResponseAssembler;
import github.devparkge.realworld.controller.comment.model.request.AddCommentRequest;
import github.devparkge.realworld.controller.comment.model.response.wrapper.CommentWrapper;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.comment.service.AddCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles/{slug}/comments")
public class CommentApiController {
    private final CommentResponseAssembler commentResponseAssembler;
    private final AddCommentService addCommentService;

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
}
