package fr.esgi.exception;

public class BadRequestRentalCarException extends RuntimeException {
    public BadRequestRentalCarException() {
        super("Request Body is incomplete or invalid");
    }
}