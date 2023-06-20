package fr.esgi.rent.api;

import fr.esgi.api.RentalPropertyResource;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import fr.esgi.exception.BadRequestRentalPropertyException;
import fr.esgi.exception.NotFoundRentalPropertyException;
import fr.esgi.mapper.RentalPropertyMapper;
import fr.esgi.service.RequesterService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

import static fr.esgi.rent.sample.HttpResponseSample.rentalPropertyGetAllResponse;
import static fr.esgi.rent.sample.HttpResponseSample.rentalPropertyGetOneResponse;
import static fr.esgi.rent.sample.RentalPropertyDtoRequestSample.oneRentalPropertyDtoRequest;
import static fr.esgi.rent.sample.RentalPropertyDtoRequestSample.oneRentalPropertyDtoRequestPatch;
import static fr.esgi.rent.sample.RentalPropertyDtoResponseSample.oneRentalPropertyDto;
import static fr.esgi.rent.sample.RentalPropertyDtoResponseSample.rentalPropertyDtoResponsesList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RentalPropertyResourceTest {


    @InjectMocks
    private RentalPropertyResource rentalPropertyResource;

    @Mock
    private RequesterService requesterService;

    @Mock
    private RentalPropertyMapper rentalPropertyMapper;

    @Mock
    private HttpResponse<String> httpResponse;

    @Test
    public void shouldGetRentalProperties() throws URISyntaxException, IOException, InterruptedException {
        when(rentalPropertyMapper.stringToDtoResponseList(rentalPropertyGetAllResponse())).thenCallRealMethod();
        when(httpResponse.body()).thenReturn(rentalPropertyGetAllResponse());
        when(requesterService.callGET(anyString())).thenReturn(httpResponse);

        List<RentalPropertyDtoResponse> rentalPropertiesResponse = rentalPropertyResource.getRentalProperties();

        assertNotNull(rentalPropertiesResponse);
        assertEquals(rentalPropertyDtoResponsesList(), rentalPropertiesResponse);

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldGetRentalPropertyById() throws URISyntaxException, IOException, InterruptedException {
        when(rentalPropertyMapper.stringToDtoResponse(rentalPropertyGetOneResponse())).thenCallRealMethod();
        when(httpResponse.body()).thenReturn(rentalPropertyGetOneResponse());
        when(httpResponse.statusCode()).thenReturn(200);
        when(requesterService.callGET(anyString())).thenReturn(httpResponse);

        RentalPropertyDtoResponse rentalPropertiesResponse = rentalPropertyResource.getRentalPropertyById(1);

        assertNotNull(rentalPropertiesResponse);
        assertEquals(oneRentalPropertyDto(), rentalPropertiesResponse);

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldNotGetRentalPropertyByIdAndThrowNotFoundException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(404); // if status == 404
        when(requesterService.callGET(anyString())).thenReturn(httpResponse);


        assertThrows(NotFoundRentalPropertyException.class, () -> rentalPropertyResource.getRentalPropertyById(1));
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);
    }

    @Test //Fail
    public void shouldPostRentalProperty() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(201);
        when(requesterService.callPOST(anyString(), any())).thenReturn(httpResponse);
        when(rentalPropertyMapper.dtoRequestToString(oneRentalPropertyDtoRequest())).thenCallRealMethod();

        try(Response response = rentalPropertyResource.createRentalProperty(oneRentalPropertyDtoRequest())){
            assertEquals(201, response.getStatus());
        }

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldNotPostRentalPropertyAndThrowBadRequestException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(400); // if status != 201 (Created)
        when(requesterService.callPOST(anyString(), any())).thenReturn(httpResponse);
        when(rentalPropertyMapper.dtoRequestToString(oneRentalPropertyDtoRequest())).thenCallRealMethod();


        assertThrows(BadRequestRentalPropertyException.class, () -> rentalPropertyResource.createRentalProperty(oneRentalPropertyDtoRequest()));

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);
    }

    @Test //Fail
    public void shouldPutRentalProperty() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(200);
        when(requesterService.callPUT(anyString(), any())).thenReturn(httpResponse);
        when(rentalPropertyMapper.dtoRequestToString(oneRentalPropertyDtoRequest())).thenCallRealMethod();

        try(Response response = rentalPropertyResource.updateRentalProperty(1, oneRentalPropertyDtoRequest())){
            assertEquals(200, response.getStatus());
        }

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);
    }

    @Test
    public void shouldNotPutRentalPropertyAndThrowBadRequestException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(400); // if status != 200 (Ok)
        when(requesterService.callPUT(anyString(), any())).thenReturn(httpResponse);
        when(rentalPropertyMapper.dtoRequestToString(oneRentalPropertyDtoRequest())).thenCallRealMethod();


        assertThrows(BadRequestRentalPropertyException.class, () -> rentalPropertyResource.updateRentalProperty(1, oneRentalPropertyDtoRequest()));

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);
    }

    @Test //Fail
    public void shouldPatchRentalProperty() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(200);
        when(requesterService.callPATCH(anyString(), any())).thenReturn(httpResponse);
        when(rentalPropertyMapper.patchDtoRequestToString(oneRentalPropertyDtoRequestPatch())).thenCallRealMethod();

        try(Response response = rentalPropertyResource.patchRentalProperty(1, oneRentalPropertyDtoRequestPatch())){
            assertEquals(200, response.getStatus());
        }

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldNotPatchRentalPropertyAndThrowBadRequestException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(400); // if status == 400 (Bad Request)
        when(requesterService.callPATCH(anyString(), any())).thenReturn(httpResponse);
        when(rentalPropertyMapper.patchDtoRequestToString(oneRentalPropertyDtoRequestPatch())).thenCallRealMethod();



        assertThrows(BadRequestRentalPropertyException.class, () -> rentalPropertyResource.patchRentalProperty(1, oneRentalPropertyDtoRequestPatch()));

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldNotPatchRentalPropertyAndThrowNotFoundException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(404); // if status == 404 (Not Found)
        when(requesterService.callPATCH(anyString(), any())).thenReturn(httpResponse);
        when(rentalPropertyMapper.patchDtoRequestToString(oneRentalPropertyDtoRequestPatch())).thenCallRealMethod();



        assertThrows(NotFoundRentalPropertyException.class, () -> rentalPropertyResource.patchRentalProperty(1, oneRentalPropertyDtoRequestPatch()));

        verifyNoMoreInteractions(rentalPropertyMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);


    }

    @Test
    public void shouldDeleteRentalProperty() throws URISyntaxException, IOException, InterruptedException {
        when(requesterService.callDELETE(anyString())).thenReturn(httpResponse); // then return ?

        rentalPropertyResource.deleteRentalProperty(1);

        verifyNoMoreInteractions(rentalPropertyMapper);

    }




}
