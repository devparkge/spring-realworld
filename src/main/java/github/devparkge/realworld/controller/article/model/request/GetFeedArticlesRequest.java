package github.devparkge.realworld.controller.article.model.request;

public record GetFeedArticlesRequest(
        Integer limit,
        Integer offset
) {
    @Override
    public Integer limit() {
        if(limit == null) return 20;
        return limit;
    }

    @Override
    public Integer offset() {
        if(offset == null) return 0;
        return offset;
    }
}
