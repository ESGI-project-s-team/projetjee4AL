package fr.esgi.rent.sample;

import fr.esgi.dto.request.RentalCarDtoRequest;
import fr.esgi.dto.request.RentalCarRequestPatchDto;

public class RentalCarDtoRequestSample {

    public static RentalCarDtoRequest oneRentalCarDtoRequest() {
        return new RentalCarDtoRequest(
                "BMW",
                "Serie 1",
                790.9,
                1550.9,
                5,
                4,
                true

        );
    }

    public static RentalCarRequestPatchDto oneRentalCarDtoRequestPatch() {
        return new RentalCarRequestPatchDto(
                790.9
        );
    }
}
