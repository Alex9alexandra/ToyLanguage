package exceptions;

public class FileOpenException extends RuntimeException {
    public FileOpenException(String message) {
        super(message);
    }
}
