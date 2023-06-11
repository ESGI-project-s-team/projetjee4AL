package fr.esgi.rent.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RentalPropertyRequestPatchDto(
        @NotNull
        @JsonProperty("rentAmount") Double rentAmount
) {
}
