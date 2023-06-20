package fr.esgi.dto.response;

public record RentalPropertyDtoResponse(
        String address,
        double area,
        String description,
        String propertyType,
        double rentAmount,
        double securityDepositAmount,
        String town
) {
}
