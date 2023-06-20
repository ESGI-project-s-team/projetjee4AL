package fr.esgi.api;

import fr.esgi.dto.request.RentalPropertyDtoRequest;
import fr.esgi.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import fr.esgi.exception.BadRequestRentalPropertyException;
import fr.esgi.exception.NotFoundRentalPropertyException;
import fr.esgi.mapper.RentalPropertyMapper;
import fr.esgi.service.RequesterService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpResponse;
import java.util.List;

@Path("/rental-properties")
public class RentalPropertyResource {

    private final RequesterService requesterService;
    private final RentalPropertyMapper rentalPropertyMapper;

    @Inject
    public RentalPropertyResource(RequesterService requesterService, RentalPropertyMapper rentalPropertyMapper) {
        this.requesterService = requesterService;
        this.rentalPropertyMapper = rentalPropertyMapper;
    }

    @GET
    public List<RentalPropertyDtoResponse> getRentalProperties() throws URISyntaxException, IOException, InterruptedException {
            HttpResponse<String> response = this.requesterService.callGET("http://localhost:8081/rent-properties-api/rental-properties");
        return rentalPropertyMapper.stringToDtoResponseList(response.body());
    }

    @GET
    @Path("/{id}")
    public RentalPropertyDtoResponse getRentalPropertyById(@PathParam("id") @Positive  int id) throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = this.requesterService.callGET("http://localhost:8081/rent-properties-api/rental-properties/" + id);

        if (response.statusCode() == 404){
            throw new NotFoundRentalPropertyException("Property " + id + " not found");
        }

        return this.rentalPropertyMapper.stringToDtoResponse(response.body());

    }

    @POST
    public Response createRentalProperty(@Valid RentalPropertyDtoRequest rentalPropertyDtoRequest) throws URISyntaxException, IOException, InterruptedException {

            String json = rentalPropertyMapper.dtoRequestToString(rentalPropertyDtoRequest);

            HttpResponse<String> response = this.requesterService.callPOST("http://localhost:8081/rent-properties-api/rental-properties", json);

            if (response.statusCode() != 201){
                throw new BadRequestRentalPropertyException("Request Body is incomplete or invalid");
            }

            return Response.status(Response.Status.CREATED).build();


    }

    @PUT
    @Path("/{id}")
    public Response updateRentalProperty(@PathParam("id") @Positive int id, @Valid RentalPropertyDtoRequest rentalPropertyDtoRequest) throws URISyntaxException, IOException, InterruptedException {

        String json = rentalPropertyMapper.dtoRequestToString(rentalPropertyDtoRequest);

        HttpResponse<String> response = this.requesterService.callPUT("http://localhost:8081/rent-properties-api/rental-properties/" + id, json);

        if (response.statusCode() != 200){
            throw new BadRequestRentalPropertyException("Request Body is incomplete or invalid");
        }

        return Response.status(Response.Status.OK).build();

    }

    @PATCH
    @Path("/{id}")
    public Response patchRentalProperty(@PathParam("id") @Positive int id, @Valid RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto) throws URISyntaxException, IOException, InterruptedException {

        String json = this.rentalPropertyMapper.patchDtoRequestToString(rentalPropertyRequestPatchDto);

        HttpResponse<String> response = this.requesterService.callPATCH("http://localhost:8081/rent-properties-api/rental-properties/" + id, json);

        if (response.statusCode() == 404){
            throw new NotFoundRentalPropertyException("Property " + id + " not found ");
        }

        if (response.statusCode() == 400){
            throw new BadRequestRentalPropertyException("Request Body is incomplete or invalid");
        }

        return Response.status(Response.Status.OK).build();

    }

    @DELETE
    @Path("/{id}")
    public void deleteRentalProperty(@PathParam("id") @Positive int id) throws URISyntaxException, IOException, InterruptedException {

        this.requesterService.callDELETE("http://localhost:8081/rent-properties-api/rental-properties/" + id);

    }


}

