package fr.esgi.rent.sample;

import fr.esgi.dto.response.RentalPropertyDtoResponse;

import java.util.List;

public class RentalPropertyDtoResponseSample {
    public static List<RentalPropertyDtoResponse> rentalPropertyDtoResponsesList() {
        RentalPropertyDtoResponse rentalProperty = oneRentalPropertyDto();

        RentalPropertyDtoResponse house = new RentalPropertyDtoResponse(
                "12 rue de la Pyramide",
                62.5,
                "Maison à louer dans banlieue calme et proche du RER",
                "HOUSE",
                1050.90,
                1400.90,
                "Champs-sur-Marne");

        return List.of(rentalProperty, house);
    }

    public static RentalPropertyDtoResponse oneRentalPropertyDto() {
        return new RentalPropertyDtoResponse(
                "77 Rue des roses",
                37.48,
                "Appartement spacieux avec vue sur l'ESGI",
                "FLAT",
                750.9,
                1200.9,
                "Paris"
        );
    }
}
