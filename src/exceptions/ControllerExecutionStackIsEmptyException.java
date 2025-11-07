package exceptions;

public class ControllerExecutionStackIsEmptyException extends RuntimeException {
    public ControllerExecutionStackIsEmptyException(String message) {
        super(message);
    }
}
