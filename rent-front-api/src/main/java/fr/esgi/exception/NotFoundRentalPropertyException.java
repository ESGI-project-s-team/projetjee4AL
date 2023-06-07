package fr.esgi.exception;

public class NotFoundRentalPropertyException extends RuntimeException {
    public NotFoundRentalPropertyException(String id) {
        super("Property " + id + " not found ");
    }
}