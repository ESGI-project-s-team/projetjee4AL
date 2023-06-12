package fr.esgi.exception;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;

public class NotFoundRentalPropertyException extends RuntimeException {
    public NotFoundRentalPropertyException(String message) {
        super(message);
    }
}