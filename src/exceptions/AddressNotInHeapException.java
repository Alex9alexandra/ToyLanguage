package exceptions;

public class AddressNotInHeapException extends RuntimeException {
    public AddressNotInHeapException(String message) {
        super(message);
    }
}
