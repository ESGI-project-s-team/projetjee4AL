package fr.esgi.dto.response;

public record RentalCarDtoResponse(
        String brand,
        String model,
        double rentAmount,
        double securityDepositAmount,
        int numberOfSeats,
        int numberOfDoors,
        boolean hasAirConditioning
) {
}
