package fr.esgi.exception;

public class NotFoundRentalPropertyException extends RuntimeException {
    public NotFoundRentalPropertyException(String message) {
        super(message);
    }
}
