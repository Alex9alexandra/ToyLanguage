package exceptions;

public class FileCloseException extends RuntimeException {
    public FileCloseException(String message) {
        super(message);
    }
}
