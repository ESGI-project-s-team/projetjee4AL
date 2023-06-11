package fr.esgi.api;


import com.google.gson.Gson;
import fr.esgi.dto.request.RentalCarDtoRequest;
import fr.esgi.dto.request.RentalCarRequestPatchDto;
import fr.esgi.dto.response.RentalCarDtoResponse;
import fr.esgi.exception.BadRequestRentalCarException;
import fr.esgi.exception.NotFoundRentalCarException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Path("/rental-cars")
public class RentalCarResource {

    public RentalCarResource(){

    }

    @GET
    public Response getRentalCars(){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-cars-api/rental-cars"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            Gson gson = new Gson();
            List<RentalCarDtoResponse> rentalCarDtoResponseList = Arrays.stream(gson.fromJson(responseBody, RentalCarDtoResponse[].class)).toList();
            return Response.ok(rentalCarDtoResponseList).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getRentalCarById(@PathParam("id") @Positive int id) {

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-cars-api/rental-cars/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404){
                throw new NotFoundRentalCarException(Integer.toString(id));
            }

            String responseBody = response.body();
            Gson gson = new Gson();
            RentalCarDtoResponse rentalCar = gson.fromJson(responseBody, RentalCarDtoResponse.class);

            return Response.ok(rentalCar).build();

        } catch (NotFoundRentalCarException e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }


    }

    @POST
    public Response createRentalCar(@Valid RentalCarDtoRequest rentalCarDtoRequest) {

        try{
            String jsonInString = new Gson().toJson(rentalCarDtoRequest);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-cars-api/rental-cars"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInString))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201){
                throw new BadRequestRentalCarException();
            }

            return Response.ok().status(Response.Status.CREATED).build();

        } catch (BadRequestRentalCarException e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response updateRentalCar(@PathParam("id") @Positive int id, @Valid RentalCarDtoRequest rentalCarDtoRequest) {
        try{
            String jsonInString = new Gson().toJson(rentalCarDtoRequest);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-cars-api/rental-cars/" + id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonInString))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200){
                throw new BadRequestRentalCarException();
            }

            return Response.ok().build();

        } catch (BadRequestRentalCarException e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PATCH
    @Path("/{id}")
    public Response patchRentalCar(@PathParam("id") @Positive int id, @Valid RentalCarRequestPatchDto rentalCarRequestPatchDto) {

        try{
            String jsonInString = new Gson().toJson(rentalCarRequestPatchDto);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-cars-api/rental-cars/" + id))
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonInString))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404){
                throw new NotFoundRentalCarException(Integer.toString(id));
            }

            if (response.statusCode() == 400){
                throw new BadRequestRentalCarException();
            }

            return Response.ok().build();

        } catch (NotFoundRentalCarException e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (BadRequestRentalCarException e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DELETE
    @Path("/{id}")
    public Response deleteRentalCar(@PathParam("id") @Positive int id) {

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-cars-api/rental-cars/" + id))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }


}

