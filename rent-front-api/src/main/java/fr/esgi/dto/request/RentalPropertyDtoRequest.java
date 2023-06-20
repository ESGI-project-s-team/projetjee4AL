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

        Integer numberOfBedrooms,
        Integer floorNumber,
        Integer numberOfFloors,
        Integer constructionYear,
        String energyClassification,
        Boolean hasElevator,
        Boolean hasIntercom,
        Boolean hasBalcony,
        Boolean hasParkingSpace

) {
}
