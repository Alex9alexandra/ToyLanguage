package exceptions;

public class ProgramStateStackIsEmptyException extends RuntimeException {
    public ProgramStateStackIsEmptyException(String message) {
        super(message);
    }
}
