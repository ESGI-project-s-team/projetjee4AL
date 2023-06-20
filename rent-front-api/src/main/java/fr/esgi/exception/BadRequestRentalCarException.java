package fr.esgi.exception;

public class BadRequestRentalCarException extends RuntimeException {
    public BadRequestRentalCarException(String message) {
        super(message);
    }
}