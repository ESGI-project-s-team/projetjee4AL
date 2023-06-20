package fr.esgi.rent.sample;

import fr.esgi.dto.request.RentalPropertyDtoRequest;
import fr.esgi.dto.request.RentalPropertyRequestPatchDto;

public class RentalPropertyDtoRequestSample {

    public static RentalPropertyDtoRequest oneRentalPropertyDtoRequest() {
        return new RentalPropertyDtoRequest(
                "77 Rue des roses",
                37.48,
                "Appartement spacieux avec vue sur l'ESGI",
                "FLAT",
                750.9,
                1200.9,
                "Paris",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null

        );
    }

    public static RentalPropertyRequestPatchDto oneRentalPropertyDtoRequestPatch() {
        return new RentalPropertyRequestPatchDto(
                750.9
        );
    }
}
