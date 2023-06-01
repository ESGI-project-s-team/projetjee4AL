package fr.esgi.rent.dto.request;

import lombok.Builder;

import jakarta.validation.constraints.NotNull;

@Builder
public record RentalPropertyRequestDto(
        @NotNull String description,
        @NotNull String town,
        @NotNull String address,
        @NotNull String propertyType,
        @NotNull double rentAmount,
        @NotNull double securityDepositAmount,
        @NotNull double area,
        @NotNull int bedroomsCount,
        int floorNumber,
        int numberOfFloors,
        int constructionYear,
        String energyClassification,
        boolean hasElevator,
        boolean hasIntercom,
        boolean hasBalcony,
        boolean hasParkingSpace) {

}
