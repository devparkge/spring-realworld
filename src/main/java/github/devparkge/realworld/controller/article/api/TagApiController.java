package github.devparkge.realworld.controller.article.api;

import github.devparkge.realworld.controller.article.model.response.TagResponse;
import github.devparkge.realworld.domain.article.service.GetTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagApiController {
    private final GetTagService getTagService;

    @GetMapping
    public TagResponse getTags() {
        return TagResponse.from(
                getTagService.getTags()
        );
    }
}
