package fr.esgi.api;

import fr.esgi.beans.PropertyType;
import fr.esgi.beans.RentalProperty;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import fr.esgi.mapper.RentalPropertyDtoMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/rental-properties")
public class RentalPropertyResource {

    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @Inject
    public RentalPropertyResource(RentalPropertyDtoMapper rentalPropertyDtoMapper) {
        this.rentalPropertyDtoMapper = rentalPropertyDtoMapper;
    }

    @GET
    public List<RentalPropertyDtoResponse> getRentalProperties() {
        /*return """

                "info": "data en dur en attendant la connexion à l'api rent properties",

                "hardData": [
                {
                "address":"77 Rue des roses",
                "area":37.48,
                "description":"Appartement spacieux avec vue sur l'ESGI",
                "propertyType":"FLAT",
                "rentAmount":750.9,
                "securityDepositAmount":1200.9,
                "town":"Paris"
                },
                {
                "address":"12 rue de la Pyramide",
                "area":62.5,
                "description":"Maison à louer dans banlieue calme et proche du RER",
                "propertyType":"HOUSE",
                "rentAmount":1050.9,
                "securityDepositAmount":1400.9,
                "town":"Champs-sur-Marne"
                }
                ]
                """;*/

        List<RentalProperty> rentals = List.of(
                new RentalProperty(1, "Appartement spacieux avec vue sur l'ESGI", "Paris", "77 Rue des roses", new PropertyType(1, "FLAT"), 750.9, 1200.9, 37.48, null, null, null, null, null, null, null, null, null),
                new RentalProperty(2, "Maison à louer dans banlieue calme et proche du RER", "Champs-sur-Marne", "12 rue de la Pyramide", new PropertyType(2, "HOUSE"), 1050.9, 1400.9, 62.5, null, null, null, null, null, null, null, null, null)
        );

        return rentals.stream().map(this.rentalPropertyDtoMapper::mapToDtoResponse).toList();


    }
}

