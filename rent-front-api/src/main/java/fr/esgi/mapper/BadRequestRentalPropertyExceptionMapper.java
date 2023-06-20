package fr.esgi.mapper;

import fr.esgi.exception.BadRequestRentalPropertyException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class BadRequestRentalPropertyExceptionMapper implements ExceptionMapper<BadRequestRentalPropertyException> {
    @Override
    public Response toResponse(BadRequestRentalPropertyException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(APPLICATION_JSON)
                .build();
    }
}
