package fr.esgi.mapper;

import fr.esgi.exception.BadRequestRentalCarException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class BadRequestRentalCarExceptionMapper implements ExceptionMapper<BadRequestRentalCarException> {
    @Override
    public Response toResponse(BadRequestRentalCarException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(APPLICATION_JSON)
                .build();
    }
}
