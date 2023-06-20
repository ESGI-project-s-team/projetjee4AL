package fr.esgi.api;


import fr.esgi.dto.request.RentalCarDtoRequest;
import fr.esgi.dto.request.RentalCarRequestPatchDto;
import fr.esgi.dto.response.RentalCarDtoResponse;
import fr.esgi.exception.BadRequestRentalCarException;
import fr.esgi.exception.NotFoundRentalCarException;
import fr.esgi.mapper.RentalCarMapper;
import fr.esgi.service.RequesterService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

@Path("/rental-cars")
public class RentalCarResource {

    private final RequesterService requesterService;
    private final RentalCarMapper rentalCarMapper;

    @Inject
    public RentalCarResource(RequesterService requesterService, RentalCarMapper rentalCarMapper){
        this.requesterService = requesterService;
        this.rentalCarMapper = rentalCarMapper;
    }

    @GET
    public List<RentalCarDtoResponse> getRentalCars() throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = this.requesterService.callGET("http://localhost:8083/rent-cars-api/rental-cars");
        return this.rentalCarMapper.stringToDtoResponseList(response.body());
    }

    @GET
    @Path("/{id}")
    public RentalCarDtoResponse getRentalCarById(@PathParam("id") @Positive int id) throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = this.requesterService.callGET("http://localhost:8083/rent-cars-api/rental-cars" + id);
        if (response.statusCode() == 404){
            throw new NotFoundRentalCarException("Car " + id + " not found");
        }
        return this.rentalCarMapper.stringToDtoResponse(response.body());
    }

    @POST
    public Response createRentalCar(@Valid RentalCarDtoRequest rentalCarDtoRequest) throws URISyntaxException, IOException, InterruptedException {

        String json = this.rentalCarMapper.dtoRequestToString(rentalCarDtoRequest);

        HttpResponse<String> response = this.requesterService.callPOST("http://localhost:8083/rent-cars-api/rental-cars", json);
        if (response.statusCode() != 201){
            throw new BadRequestRentalCarException("Request Body is incomplete or invalid");
        }

        return Response.status(Response.Status.CREATED).build();

    }

    @PUT
    @Path("/{id}")
    public Response updateRentalCar(@PathParam("id") @Positive int id, @Valid RentalCarDtoRequest rentalCarDtoRequest) throws URISyntaxException, IOException, InterruptedException {

        String json = this.rentalCarMapper.dtoRequestToString(rentalCarDtoRequest);

        HttpResponse<String> response = this.requesterService.callPUT("http://localhost:8083/rent-cars-api/rental-cars" + id, json);
        if (response.statusCode() != 200){
            throw new BadRequestRentalCarException("Request Body is incomplete or invalid");
        }

        return Response.status(Response.Status.OK).build();

    }

    @PATCH
    @Path("/{id}")
    public Response patchRentalCar(@PathParam("id") @Positive int id, @Valid RentalCarRequestPatchDto rentalCarRequestPatchDto) throws URISyntaxException, IOException, InterruptedException {

        String json = this.rentalCarMapper.patchDtoRequestToString(rentalCarRequestPatchDto);

        HttpResponse<String> response = this.requesterService.callPATCH("http://localhost:8083/rent-cars-api/rental-cars" + id, json);

        if (response.statusCode() == 404){
            throw new NotFoundRentalCarException("Car " + id + " not found");
        }

        if (response.statusCode() == 400){
            throw new BadRequestRentalCarException("Request Body is incomplete or invalid");
        }

        return Response.status(Response.Status.OK).build();

    }

    @DELETE
    @Path("/{id}")
    public void deleteRentalCar(@PathParam("id") @Positive int id) throws URISyntaxException, IOException, InterruptedException {

        this.requesterService.callDELETE("http://localhost:8083/rent-cars-api/rental-cars" + id);

    }


}

