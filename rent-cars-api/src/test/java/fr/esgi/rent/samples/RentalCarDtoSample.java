package fr.esgi.rent.samples;

import fr.esgi.rent.dto.request.RentalCarRequestDto;
import fr.esgi.rent.dto.request.RentalCarRequestPatchDto;
import fr.esgi.rent.dto.response.RentalCarResponseDto;

import java.util.List;

public class RentalCarDtoSample {

    public static List<RentalCarResponseDto> rentalCarResponseList() {
        RentalCarResponseDto rentalCar = oneRentalCarResponse();

        return List.of(rentalCar);
    }

    public static RentalCarResponseDto oneRentalCarResponse() {
        return RentalCarResponseDto.builder()
                .brand("Audi")
                .model("A3")
                .rentAmount(3000.0)
                .securityDepositAmount(1000.0)
                .numberOfSeats(5)
                .numberOfDoors(5)
                .hasAirConditioning(true)
                .build();
    }

    public static RentalCarRequestDto oneRentalCarRequest() {
        return RentalCarRequestDto.builder()
                .brand("Audi")
                .model("A3")
                .rentAmount(3000.0)
                .securityDepositAmount(1000.0)
                .numberOfSeats(5)
                .numberOfDoors(5)
                .hasAirConditioning(true)
                .build();
    }

    public static RentalCarRequestPatchDto oneRentalCarRequestPatch() {
        return RentalCarRequestPatchDto.builder()
                .rentAmount(750.90)
                .build();
    }
}
