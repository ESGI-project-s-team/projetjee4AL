package fr.esgi.rent.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RentalPropertyRequestPatchDto(
        @NotNull double rentAmount
) {
}
