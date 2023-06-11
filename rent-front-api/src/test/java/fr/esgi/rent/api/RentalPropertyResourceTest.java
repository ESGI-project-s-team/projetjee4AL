package fr.esgi.rent.api;

import fr.esgi.api.RentalPropertyResource;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import jakarta.faces.context.ExceptionHandler;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.RuntimeDelegate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;

import static fr.esgi.rent.sample.RentalPropertyDtoResponseSample.rentalPropertyDtoResponsesList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RentalPropertyResourceTest {

    @Spy
    @InjectMocks
    private RentalPropertyResource rentalPropertyResource;

    @Test
    public void shouldGetRentalProperties() throws URISyntaxException, IOException, InterruptedException {

        List<RentalPropertyDtoResponse> rentalPropertiesResponse = rentalPropertyResource.getRentalProperties();

        assertNotNull(rentalPropertiesResponse);

    }

    @Test
    public void shouldGetRentalPropertyById() throws URISyntaxException, IOException, InterruptedException {

        RentalPropertyDtoResponse rentalPropertyResponse = rentalPropertyResource.getRentalPropertyById(1);

        
        assertNotNull(rentalPropertyResponse);

    }




}
