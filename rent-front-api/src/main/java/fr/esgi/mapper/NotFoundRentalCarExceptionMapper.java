package fr.esgi.mapper;

import fr.esgi.exception.NotFoundRentalCarException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class NotFoundRentalCarExceptionMapper implements ExceptionMapper<NotFoundRentalCarException> {

    @Override
    public Response toResponse(NotFoundRentalCarException exception) {
        return Response.status(404)
                .entity(exception.getMessage())
                .type(APPLICATION_JSON)
                .build();
    }

}