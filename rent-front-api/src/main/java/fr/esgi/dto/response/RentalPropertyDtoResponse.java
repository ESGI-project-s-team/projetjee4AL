package fr.esgi.dto.response;

import fr.esgi.beans.PropertyType;

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
