package fr.esgi.rent.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record RentalCarResponseDto(
        @JsonProperty("brand") String brand,
        @JsonProperty("model") String model,
        @JsonProperty("rentAmount") double rentAmount,
        @JsonProperty("securityDepositAmount") double securityDepositAmount,
        @JsonProperty("numberOfSeats") Integer numberOfSeats,
        @JsonProperty("numberOfDoors") Integer numberOfDoors,
        @JsonProperty("hasAirConditioning") Boolean hasAirConditioning
) {

}
