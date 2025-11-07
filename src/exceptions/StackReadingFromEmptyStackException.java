package exceptions;

public class StackReadingFromEmptyStackException extends RuntimeException {
    public StackReadingFromEmptyStackException(String message) {
        super(message);
    }
}
