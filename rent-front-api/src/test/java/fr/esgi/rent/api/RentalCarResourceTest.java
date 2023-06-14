package fr.esgi.rent.api;

import fr.esgi.api.RentalCarResource;
import fr.esgi.api.RentalPropertyResource;
import fr.esgi.dto.response.RentalCarDtoResponse;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import fr.esgi.exception.BadRequestRentalCarException;
import fr.esgi.exception.BadRequestRentalPropertyException;
import fr.esgi.exception.NotFoundRentalCarException;
import fr.esgi.exception.NotFoundRentalPropertyException;
import fr.esgi.mapper.RentalCarMapper;
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

import static fr.esgi.rent.sample.HttpResponseSample.*;
import static fr.esgi.rent.sample.RentalCarDtoRequestSample.oneRentalCarDtoRequest;
import static fr.esgi.rent.sample.RentalCarDtoRequestSample.oneRentalCarDtoRequestPatch;
import static fr.esgi.rent.sample.RentalCarDtoResponseSample.oneRentalCarDto;
import static fr.esgi.rent.sample.RentalCarDtoResponseSample.rentalCarDtoResponsesList;
import static fr.esgi.rent.sample.RentalPropertyDtoRequestSample.oneRentalPropertyDtoRequest;
import static fr.esgi.rent.sample.RentalPropertyDtoRequestSample.oneRentalPropertyDtoRequestPatch;
import static fr.esgi.rent.sample.RentalPropertyDtoResponseSample.oneRentalPropertyDto;
import static fr.esgi.rent.sample.RentalPropertyDtoResponseSample.rentalPropertyDtoResponsesList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RentalCarResourceTest {


    @InjectMocks
    private RentalCarResource rentalCarResource;

    @Mock
    private RequesterService requesterService;

    @Mock
    private RentalCarMapper rentalCarMapper;

    @Mock
    private HttpResponse<String> httpResponse;

    @Test
    public void shouldGetRentalCars() throws URISyntaxException, IOException, InterruptedException {
        when(rentalCarMapper.stringToDtoResponseList(rentalCarGetAllResponse())).thenCallRealMethod();
        when(httpResponse.body()).thenReturn(rentalCarGetAllResponse());
        when(requesterService.callGET(anyString())).thenReturn(httpResponse);

        List<RentalCarDtoResponse> rentalCarsResponse = rentalCarResource.getRentalCars();

        assertNotNull(rentalCarsResponse);
        assertEquals(rentalCarDtoResponsesList(), rentalCarsResponse);

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldGetRentalPropertyById() throws URISyntaxException, IOException, InterruptedException {
        when(rentalCarMapper.stringToDtoResponse(rentalCarGetOneResponse())).thenCallRealMethod();
        when(httpResponse.body()).thenReturn(rentalCarGetOneResponse());
        when(httpResponse.statusCode()).thenReturn(200);
        when(requesterService.callGET(anyString())).thenReturn(httpResponse);

        RentalCarDtoResponse rentalCarResponse = rentalCarResource.getRentalCarById(1);

        assertNotNull(rentalCarResponse);
        assertEquals(oneRentalCarDto(), rentalCarResponse);

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldNotGetRentalPropertyByIdAndThrowNotFoundException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(404); // if status == 404
        when(requesterService.callGET(anyString())).thenReturn(httpResponse);


        assertThrows(NotFoundRentalCarException.class, () -> {rentalCarResource.getRentalCarById(1);});
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);
    }

    @Test //Fail
    public void shouldPostRentalProperty() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(201);
        when(requesterService.callPOST(anyString(), any())).thenReturn(httpResponse);
        when(rentalCarMapper.dtoRequestToString(oneRentalCarDtoRequest())).thenCallRealMethod();

        Response response = rentalCarResource.createRentalCar(oneRentalCarDtoRequest());


        assertEquals(201, response.getStatus());

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);


    }

    @Test
    public void shouldNotPostRentalPropertyAndThrowBadRequestException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(400); // if status != 201 (Created)
        when(requesterService.callPOST(anyString(), any())).thenReturn(httpResponse);
        when(rentalCarMapper.dtoRequestToString(oneRentalCarDtoRequest())).thenCallRealMethod();


        assertThrows(BadRequestRentalCarException.class, () -> {rentalCarResource.createRentalCar(oneRentalCarDtoRequest());});

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);
    }

    @Test //Fail
    public void shouldPutRentalProperty() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(200);
        when(requesterService.callPUT(anyString(), any())).thenReturn(httpResponse);
        when(rentalCarMapper.dtoRequestToString(oneRentalCarDtoRequest())).thenCallRealMethod();

        Response response = rentalCarResource.updateRentalCar(1, oneRentalCarDtoRequest());


        assertEquals(200, response.getStatus());

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);
    }

    @Test
    public void shouldNotPutRentalPropertyAndThrowBadRequestException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(400); // if status != 200 (Ok)
        when(requesterService.callPUT(anyString(), any())).thenReturn(httpResponse);
        when(rentalCarMapper.dtoRequestToString(oneRentalCarDtoRequest())).thenCallRealMethod();


        assertThrows(BadRequestRentalCarException.class, () -> {rentalCarResource.updateRentalCar(1, oneRentalCarDtoRequest());});

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);
    }

    @Test //Fail
    public void shouldPatchRentalProperty() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(200);
        when(requesterService.callPATCH(anyString(), any())).thenReturn(httpResponse);
        when(rentalCarMapper.patchDtoRequestToString(oneRentalCarDtoRequestPatch())).thenCallRealMethod();

        Response response = rentalCarResource.patchRentalCar(1, oneRentalCarDtoRequestPatch());


        assertEquals(200, response.getStatus());

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldNotPatchRentalPropertyAndThrowBadRequestException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(400); // if status == 400 (Bad Request)
        when(requesterService.callPATCH(anyString(), any())).thenReturn(httpResponse);
        when(rentalCarMapper.patchDtoRequestToString(oneRentalCarDtoRequestPatch())).thenCallRealMethod();



        assertThrows(BadRequestRentalCarException.class, () -> {rentalCarResource.patchRentalCar(1, oneRentalCarDtoRequestPatch());});

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);

    }

    @Test
    public void shouldNotPatchRentalPropertyAndThrowNotFoundException() throws URISyntaxException, IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(404); // if status == 404 (Not Found)
        when(requesterService.callPATCH(anyString(), any())).thenReturn(httpResponse);
        when(rentalCarMapper.patchDtoRequestToString(oneRentalCarDtoRequestPatch())).thenCallRealMethod();



        assertThrows(NotFoundRentalCarException.class, () -> {rentalCarResource.patchRentalCar(1, oneRentalCarDtoRequestPatch());});

        verifyNoMoreInteractions(rentalCarMapper);
        verifyNoMoreInteractions(requesterService);
        verifyNoMoreInteractions(httpResponse);


    }

    @Test
    public void shouldDeleteRentalProperty() throws URISyntaxException, IOException, InterruptedException {
        when(requesterService.callDELETE(anyString())).thenReturn(httpResponse); // then return ?

        rentalCarResource.deleteRentalCar(1);

        verifyNoMoreInteractions(rentalCarMapper);

    }




}
