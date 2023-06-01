package fr.esgi.api;

import fr.esgi.beans.PropertyType;
import fr.esgi.beans.RentalProperty;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import fr.esgi.exception.NotFoundRentalPropertyException;
import fr.esgi.mapper.RentalPropertyDtoMapper;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

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

        List<RentalProperty> rentals = List.of(
                new RentalProperty(1, "Appartement spacieux avec vue sur l'ESGI", "Paris", "77 Rue des roses", new PropertyType(1, "FLAT"), 750.9, 1200.9, 37.48, null, null, null, null, null, null, null, null, null),
                new RentalProperty(2, "Maison à louer dans banlieue calme et proche du RER", "Champs-sur-Marne", "12 rue de la Pyramide", new PropertyType(2, "HOUSE"), 1050.9, 1400.9, 62.5, null, null, null, null, null, null, null, null, null)
        );

        return Response.ok(rentals.stream().map(this.rentalPropertyDtoMapper::mapToDto).toList()).build();

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

}

