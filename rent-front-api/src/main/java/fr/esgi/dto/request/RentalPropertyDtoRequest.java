package fr.esgi.dto.request;

import jakarta.validation.constraints.NotNull;

public record RentalPropertyDtoRequest(
        @NotNull
        String address,
        @NotNull
        Double area,
        @NotNull
        String description,
        @NotNull
        String propertyType,
        @NotNull
        Double rentAmount,
        @NotNull
        Double securityDepositAmount,
        @NotNull
        String town,

        int numberOfBedrooms,
        int floorNumber,
        int numberOfFloors,
        int constructionYear,
        String energyClassification,
        boolean hasElevator,
        boolean hasIntercom,
        boolean hasBalcony,
        boolean hasParkingSpace

) {
}
