package exceptions;

public class ExecutionCompletedException extends RuntimeException {
    public ExecutionCompletedException(String message) {
        super(message);
    }
}
