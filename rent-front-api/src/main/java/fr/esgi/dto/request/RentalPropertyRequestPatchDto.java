package fr.esgi.dto.request;

import jakarta.validation.constraints.NotNull;

public record RentalPropertyRequestPatchDto(
        @NotNull
        Double rentAmount
) {
}
