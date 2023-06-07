package fr.esgi.exception;

public class BadRequestRentalPropertyException extends RuntimeException {
    public BadRequestRentalPropertyException() {
        super("Request Body is incomplete or invalid");
    }
}