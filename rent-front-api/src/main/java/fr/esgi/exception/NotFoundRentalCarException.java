package fr.esgi.exception;

public class NotFoundRentalCarException extends RuntimeException {
    public NotFoundRentalCarException(String id) {
        super("Car " + id + " not found ");
    }
}