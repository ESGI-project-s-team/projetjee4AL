package fr.esgi.rent.sample;

import fr.esgi.dto.response.RentalCarDtoResponse;

import java.util.List;

public class RentalCarDtoResponseSample {
    public static List<RentalCarDtoResponse> rentalCarDtoResponsesList() {
        RentalCarDtoResponse rentalCar = oneRentalCarDto();

        RentalCarDtoResponse mercedes = new RentalCarDtoResponse(
                "Mercedes",
                "Classe C Hybride",
                990.9,
                2400.9,
                5,
                4,
                true);

        return List.of(rentalCar, mercedes);
    }

    public static RentalCarDtoResponse oneRentalCarDto() {
        return new RentalCarDtoResponse(
                "BMW",
                "Serie 1",
                790.9,
                1550.9,
                5,
                4,
                true
        );
    }
}
