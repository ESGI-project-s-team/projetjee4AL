package fr.esgi.rent.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RentalCarEntityTest {

    @Test
    public void testRentalCarEntity(){
        RentalCarEntity rentalCarEntity = new RentalCarEntity();
        rentalCarEntity.setId(1);
        rentalCarEntity.setBrand("brand");
        rentalCarEntity.setModel("model");
        rentalCarEntity.setRentAmount(1);
        rentalCarEntity.setSecurityDepositAmount(1);
        rentalCarEntity.setNumberOfSeats(1);
        rentalCarEntity.setNumberOfDoors(1);
        rentalCarEntity.setHasAirConditioning(true);

        assertThat(rentalCarEntity.getId()).isEqualTo(1);
        assertThat(rentalCarEntity.getBrand()).isEqualTo("brand");
        assertThat(rentalCarEntity.getModel()).isEqualTo("model");
        assertThat(rentalCarEntity.getRentAmount()).isEqualTo(1);
        assertThat(rentalCarEntity.getSecurityDepositAmount()).isEqualTo(1);
        assertThat(rentalCarEntity.getNumberOfSeats()).isEqualTo(1);
        assertThat(rentalCarEntity.getNumberOfDoors()).isEqualTo(1);
        assertThat(rentalCarEntity.isHasAirConditioning()).isTrue();
    }

    @Test
    public void testRentalCarEntityConstructor(){
        RentalCarEntity rentalCarEntity = new RentalCarEntity(
                1,
                "brand",
                "model",
                750.90,
                1200.90,
                5,
                5,
                true
        );

        assertThat(rentalCarEntity.getId()).isEqualTo(1);
        assertThat(rentalCarEntity.getBrand()).isEqualTo("brand");
        assertThat(rentalCarEntity.getModel()).isEqualTo("model");
        assertThat(rentalCarEntity.getRentAmount()).isEqualTo(750.90);
        assertThat(rentalCarEntity.getSecurityDepositAmount()).isEqualTo(1200.90);
        assertThat(rentalCarEntity.getNumberOfSeats()).isEqualTo(5);
        assertThat(rentalCarEntity.getNumberOfDoors()).isEqualTo(5);
        assertThat(rentalCarEntity.isHasAirConditioning()).isTrue();
    }
}
