package github.devparkge.realworld.exception;

public class InvalidCommentBelogsToArticleException extends RuntimeException {
    public InvalidCommentBelogsToArticleException() {
        super("This comment is not belogs to article.");
    }
}
