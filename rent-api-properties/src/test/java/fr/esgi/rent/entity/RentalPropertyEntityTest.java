package fr.esgi.rent.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(RentalPropertyEntity.class)

public class RentalPropertyEntityTest {
    @Test
    public void testRentalPropertyEntity() {
        RentalPropertyEntity rentalPropertyEntity = new RentalPropertyEntity();
        rentalPropertyEntity.setId(1);
        rentalPropertyEntity.setDescription("description");
        rentalPropertyEntity.setTown("town");
        rentalPropertyEntity.setAddress("address");
        rentalPropertyEntity.setPropertyType(new PropertyTypeEntity());
        rentalPropertyEntity.setRentAmount(1);
        rentalPropertyEntity.setSecurityDepositAmount(1);
        rentalPropertyEntity.setArea(1);
        rentalPropertyEntity.setNumberOfBedrooms(1);
        rentalPropertyEntity.setFloorNumber(1);
        rentalPropertyEntity.setNumberOfFloors(1);
        rentalPropertyEntity.setConstructionYear(1);
        rentalPropertyEntity.setEnergyClassification(new EnergyClassificationEntity());
        rentalPropertyEntity.setHasElevator(true);
        rentalPropertyEntity.setHasIntercom(true);
        rentalPropertyEntity.setHasBalcony(true);
        rentalPropertyEntity.setHasParkingSpace(true);

        assertThat(rentalPropertyEntity.getId()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getDescription()).isEqualTo("description");
        assertThat(rentalPropertyEntity.getTown()).isEqualTo("town");
        assertThat(rentalPropertyEntity.getAddress()).isEqualTo("address");
        assertThat(rentalPropertyEntity.getPropertyType()).isNotNull();
        assertThat(rentalPropertyEntity.getRentAmount()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getSecurityDepositAmount()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getArea()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getNumberOfBedrooms()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getFloorNumber()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getNumberOfFloors()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getConstructionYear()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getEnergyClassification()).isNotNull();
        assertThat(rentalPropertyEntity.isHasElevator()).isEqualTo(true);
        assertThat(rentalPropertyEntity.isHasIntercom()).isEqualTo(true);
        assertThat(rentalPropertyEntity.isHasBalcony()).isEqualTo(true);
        assertThat(rentalPropertyEntity.isHasParkingSpace()).isEqualTo(true);
    }

    //with constructor
    @Test
    public void testRentalPropertyEntityConstructor() {
        RentalPropertyEntity rentalPropertyEntity = new RentalPropertyEntity(
                1,
                "description",
                "Paris",
                "77 Rue des roses",
                new PropertyTypeEntity(1, "Appartement"),
                750.90,
                1200.90,
                37.48,
                2,
                1,
                3,
                1990,
                new EnergyClassificationEntity(1, "B"),
                false,
                false,
                true,
                false);

        assertThat(rentalPropertyEntity.getId()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getDescription()).isEqualTo("description");
        assertThat(rentalPropertyEntity.getTown()).isEqualTo("Paris");
        assertThat(rentalPropertyEntity.getAddress()).isEqualTo("77 Rue des roses");
        assertThat(rentalPropertyEntity.getPropertyType()).isNotNull();
        assertThat(rentalPropertyEntity.getRentAmount()).isEqualTo(750.90);
        assertThat(rentalPropertyEntity.getSecurityDepositAmount()).isEqualTo(1200.90);
        assertThat(rentalPropertyEntity.getArea()).isEqualTo(37.48);
        assertThat(rentalPropertyEntity.getNumberOfBedrooms()).isEqualTo(2);
        assertThat(rentalPropertyEntity.getFloorNumber()).isEqualTo(1);
        assertThat(rentalPropertyEntity.getNumberOfFloors()).isEqualTo(3);
        assertThat(rentalPropertyEntity.getConstructionYear()).isEqualTo(1990);
        assertThat(rentalPropertyEntity.getEnergyClassification()).isNotNull();
        assertThat(rentalPropertyEntity.isHasElevator()).isEqualTo(false);
        assertThat(rentalPropertyEntity.isHasIntercom()).isEqualTo(false);
        assertThat(rentalPropertyEntity.isHasBalcony()).isEqualTo(true);
        assertThat(rentalPropertyEntity.isHasParkingSpace()).isEqualTo(false);

    }
}
