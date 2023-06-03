package fr.esgi.api;

import fr.esgi.beans.PropertyType;
import fr.esgi.beans.RentalProperty;
import fr.esgi.dto.request.RentalPropertyDtoRequest;
import fr.esgi.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import fr.esgi.mapper.RentalPropertyDtoMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Path("/rental-properties")
public class RentalPropertyResource {

    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @Inject
    public RentalPropertyResource(RentalPropertyDtoMapper rentalPropertyDtoMapper) {
        this.rentalPropertyDtoMapper = rentalPropertyDtoMapper;
    }

    @GET
    public Response getRentalProperties() {

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/rent-properties-api/rental-properties"))
                    .GET()
                    .build();
            System.out.println("Sending request to " + request.uri());
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Response.ok(response.body()).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Path("/{id}")
    public Response getRentalPropertyById(@PathParam("id") @Positive  int id) {
        List<RentalProperty> rentals = List.of(
                new RentalProperty(1, "Appartement spacieux avec vue sur l'ESGI", "Paris", "77 Rue des roses", new PropertyType(1, "FLAT"), 750.9, 1200.9, 37.48, null, null, null, null, null, null, null, null, null),
                new RentalProperty(2, "Maison à louer dans banlieue calme et proche du RER", "Champs-sur-Marne", "12 rue de la Pyramide", new PropertyType(2, "HOUSE"), 1050.9, 1400.9, 62.5, null, null, null, null, null, null, null, null, null)
        );

        Optional<RentalPropertyDtoResponse> optRentalPropertyDtoResponse = rentals.stream()
                .filter(rentalProperty -> rentalProperty.id() == id)
                .map(rentalPropertyDtoMapper::mapToDto)
                .findAny();

        if (optRentalPropertyDtoResponse.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(optRentalPropertyDtoResponse.get()).build();


    }

    @POST
    public Response createRentalProperty(@Valid RentalPropertyDtoRequest rentalPropertyDtoRequest) {

        RentalProperty rentalProperty = rentalPropertyDtoMapper.mapToBean(rentalPropertyDtoRequest);

        return Response.status(Response.Status.CREATED).build();

    }

    @PUT
    @Path("/{id}")
    public Response updateRentalProperty(@PathParam("id") @Positive int id, @Valid RentalPropertyDtoRequest rentalPropertyDtoRequest) {

        RentalProperty rentalProperty = rentalPropertyDtoMapper.mapToBean(rentalPropertyDtoRequest);

        if (id != 2)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().build();

    }

    @PATCH
    @Path("/{id}")
    public Response patchRentalProperty(@PathParam("id") @Positive int id, @Valid RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto) {

        if (id != 2)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteRentalProperty(@PathParam("id") @Positive int id) {

        return Response.status(Response.Status.NO_CONTENT).build();

    }


}

