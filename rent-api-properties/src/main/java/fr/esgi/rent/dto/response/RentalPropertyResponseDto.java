package fr.esgi.rent.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record RentalPropertyResponseDto(
        @JsonProperty("description") String description,
        @JsonProperty("address") String address,
        @JsonProperty("town") String town,
        @JsonProperty("propertyType") String propertyType,
        @JsonProperty("rentAmount") double rentAmount,
        @JsonProperty("securityDepositAmount") double securityDepositAmount,
        @JsonProperty("area") double area
) {

}
