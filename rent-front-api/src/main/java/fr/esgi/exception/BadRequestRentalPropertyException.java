package fr.esgi.exception;

public class BadRequestRentalPropertyException extends RuntimeException {
    public BadRequestRentalPropertyException(String message) {
        super(message);
    }
}