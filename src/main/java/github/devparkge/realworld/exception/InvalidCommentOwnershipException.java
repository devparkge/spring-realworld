package github.devparkge.realworld.exception;

public class InvalidCommentOwnershipException extends RuntimeException {
    public InvalidCommentOwnershipException() {
        super("The author is not owner of this comment.");
    }
}
