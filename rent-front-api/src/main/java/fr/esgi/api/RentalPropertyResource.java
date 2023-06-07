package fr.esgi.api;

import com.google.gson.Gson;
import fr.esgi.dto.request.RentalPropertyDtoRequest;
import fr.esgi.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Path("/rental-properties")
public class RentalPropertyResource {

    @Inject
    public RentalPropertyResource() {
    }

    @GET
    public Response getRentalProperties() {

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-properties-api/rental-properties"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            Gson gson = new Gson();
            List<RentalPropertyDtoResponse> rentalPropertyList = Arrays.stream(gson.fromJson(responseBody, RentalPropertyDtoResponse[].class)).toList();
            return Response.ok(rentalPropertyList).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Path("/{id}")
    public Response getRentalPropertyById(@PathParam("id") @Positive  int id) {

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-properties-api/rental-properties/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404){
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            String responseBody = response.body();
            Gson gson = new Gson();
            RentalPropertyDtoResponse rentalProperty = gson.fromJson(responseBody, RentalPropertyDtoResponse.class);

            return Response.ok(rentalProperty).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }


    }

    @POST
    public Response createRentalProperty(@Valid RentalPropertyDtoRequest rentalPropertyDtoRequest) {

        try{
            String jsonInString = new Gson().toJson(rentalPropertyDtoRequest);

                    HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-properties-api/rental-properties"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInString))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            return Response.ok().status(Response.Status.CREATED).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response updateRentalProperty(@PathParam("id") @Positive int id, @Valid RentalPropertyDtoRequest rentalPropertyDtoRequest) {
        try{
            String jsonInString = new Gson().toJson(rentalPropertyDtoRequest);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-properties-api/rental-properties/" + id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonInString))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            return Response.ok().build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PATCH
    @Path("/{id}")
    public Response patchRentalProperty(@PathParam("id") @Positive int id, @Valid RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto) {

        try{
            String jsonInString = new Gson().toJson(rentalPropertyRequestPatchDto);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-properties-api/rental-properties/" + id))
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonInString))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404){
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (response.statusCode() == 400){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            return Response.ok().build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DELETE
    @Path("/{id}")
    public Response deleteRentalProperty(@PathParam("id") @Positive int id) {

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-properties-api/rental-properties/" + id))
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

