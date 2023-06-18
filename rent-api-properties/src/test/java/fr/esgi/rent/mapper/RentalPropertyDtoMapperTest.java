package fr.esgi.rent.mapper;

import fr.esgi.rent.entity.RentalPropertyEntity;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.esgi.rent.samples.RentalPropertyDtoSample.oneRentalPropertyRequest;
import static fr.esgi.rent.samples.RentalPropertyDtoSample.oneRentalPropertyResponse;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.oneRentalPropertyEntity;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalPropertyDtoMapperTest {

    @Test
    void shouldMapToDto() {
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyResponseDto expectedRentalPropertyResponseDto = oneRentalPropertyResponse();

        RentalPropertyDtoMapper rentalPropertyDtoMapper = new RentalPropertyDtoMapper();

        RentalPropertyResponseDto rentalPropertyResponseDto = rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity);

        assertThat(rentalPropertyResponseDto).isEqualTo(expectedRentalPropertyResponseDto);
    }

    @Test
    void shouldMapToDtoList() {
        List<RentalPropertyEntity> rentalPropertyEntities = rentalPropertyEntities();

        RentalPropertyDtoMapper rentalPropertyDtoMapper = new RentalPropertyDtoMapper();

        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyDtoMapper.mapToDtoList(rentalPropertyEntities);

        assertThat(rentalPropertyResponseList).isNotNull()
                .hasSize(1)
                .extracting("description", "address", "town", "propertyType", "rentAmount", "securityDepositAmount", "area")
                .containsExactlyInAnyOrder(
                        tuple("Appartement spacieux avec vue sur l'ESGI",
                                "77 Rue des roses",
                                "Paris",
                                "Appartement",
                                750.90,
                                1200.90,
                                37.48)
                );
    }

    //mapToEntity
    @Test
    void shouldMapToEntity() {
        RentalPropertyDtoMapper rentalPropertyDtoMapper = new RentalPropertyDtoMapper();
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(oneRentalPropertyRequest());
        RentalPropertyEntity rentalPropertyEntityExpected = oneRentalPropertyEntity();
        assertEquals(rentalPropertyEntityExpected.getDescription(), rentalPropertyEntity.getDescription());
        assertEquals(rentalPropertyEntityExpected.getTown(), rentalPropertyEntity.getTown());
        assertEquals(rentalPropertyEntityExpected.getAddress(), rentalPropertyEntity.getAddress());
        assertEquals(rentalPropertyEntityExpected.getPropertyType().getDesignation(), rentalPropertyEntity.getPropertyType().getDesignation());
        assertEquals(rentalPropertyEntityExpected.getRentAmount(), rentalPropertyEntity.getRentAmount());
        assertEquals(rentalPropertyEntityExpected.getSecurityDepositAmount(), rentalPropertyEntity.getSecurityDepositAmount());
        assertEquals(rentalPropertyEntityExpected.getArea(), rentalPropertyEntity.getArea());
        assertEquals(rentalPropertyEntityExpected.getNumberOfBedrooms(), rentalPropertyEntity.getNumberOfBedrooms());
        assertEquals(rentalPropertyEntityExpected.getFloorNumber(), rentalPropertyEntity.getFloorNumber());
        assertEquals(rentalPropertyEntityExpected.getNumberOfFloors(), rentalPropertyEntity.getNumberOfFloors());
        assertEquals(rentalPropertyEntityExpected.getConstructionYear(), rentalPropertyEntity.getConstructionYear());
        assertEquals(rentalPropertyEntityExpected.getEnergyClassification().getDesignation(), rentalPropertyEntity.getEnergyClassification().getDesignation());
        assertEquals(rentalPropertyEntityExpected.isHasElevator(), rentalPropertyEntity.isHasElevator());
        assertEquals(rentalPropertyEntityExpected.isHasIntercom(), rentalPropertyEntity.isHasIntercom());
        assertEquals(rentalPropertyEntityExpected.isHasBalcony(), rentalPropertyEntity.isHasBalcony());
        assertEquals(rentalPropertyEntityExpected.isHasParkingSpace(), rentalPropertyEntity.isHasParkingSpace());
    }

}
