package fr.esgi.mapper;

import fr.esgi.exception.NotFoundRentalPropertyException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class NotFoundRentalPorpertyExceptionMapper implements ExceptionMapper<NotFoundRentalPropertyException> {

    @Override
    public Response toResponse(NotFoundRentalPropertyException exception) {
        return Response.status(404)
                .entity(exception.getMessage())
                .type(APPLICATION_JSON)
                .build();
    }

}