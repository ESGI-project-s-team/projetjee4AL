package fr.esgi.rent.samples;

import fr.esgi.rent.entity.RentalCarEntity;

import java.util.List;

public class RentalCarEntitySample {
    public static List<RentalCarEntity> rentalCarEntities() {
        RentalCarEntity rentalCar = oneRentalCarEntity();

        return List.of(rentalCar);
    }

    public static RentalCarEntity oneRentalCarEntity() {
        return RentalCarEntity.builder()
                .id(1)
                .brand("Audi")
                .model("A3")
                .rentAmount(3000.0)
                .securityDepositAmount(1000.0)
                .numberOfSeats(5)
                .numberOfDoors(5)
                .hasAirConditioning(true)
                .build();
    }

}
