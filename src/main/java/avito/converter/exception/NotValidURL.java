package avito.converter.exception;

public class NotValidURL extends RuntimeException{
    public NotValidURL(String message) {
        super(message);
    }

    public NotValidURL(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidURL() {
        super();
    }
}
