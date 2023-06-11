package fr.esgi.rent.exception;

public class NotFoundCarException extends RuntimeException {
    public NotFoundCarException(String message) {
        super(message);
    }
}
