package fr.esgi.rent.mapper;

import fr.esgi.exception.BadRequestRentalCarException;
import fr.esgi.mapper.BadRequestRentalCarExceptionMapper;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class BadRequestRentalCarExceptionMapperTest {

    @InjectMocks
    private BadRequestRentalCarExceptionMapper badRequestRentalCarExceptionMapper;

    @Test
    public void shouldBeUseOnThrow(){

        BadRequestRentalCarException exception = new BadRequestRentalCarException("test");
        Response res = badRequestRentalCarExceptionMapper.toResponse(exception);
        assertEquals(res.getStatus(), 400);
        assertEquals(res.getEntity(), "test");


    }

}
