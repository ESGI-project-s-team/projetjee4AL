package fr.esgi.dto.request;

import jakarta.validation.constraints.NotNull;

public record RentalCarRequestPatchDto(
        @NotNull
        Double rentAmount
) {
}
