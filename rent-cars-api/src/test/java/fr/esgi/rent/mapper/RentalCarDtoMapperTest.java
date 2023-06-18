package fr.esgi.rent.mapper;

import fr.esgi.rent.dto.response.RentalCarResponseDto;
import fr.esgi.rent.entity.RentalCarEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.esgi.rent.samples.RentalCarDtoSample.oneRentalCarRequest;
import static fr.esgi.rent.samples.RentalCarDtoSample.oneRentalCarResponse;
import static fr.esgi.rent.samples.RentalCarEntitySample.oneRentalCarEntity;
import static fr.esgi.rent.samples.RentalCarEntitySample.rentalCarEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalCarDtoMapperTest {

    @Test
    void shouldMapToDto() {

        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto expectedRentalCarResponseDto = oneRentalCarResponse();
        RentalCarDtoMapper rentalCarDtoMapper = new RentalCarDtoMapper();

        RentalCarResponseDto rentalCarResponseDto = rentalCarDtoMapper.mapToDto(rentalCarEntity);

        assertThat(rentalCarResponseDto).isEqualTo(expectedRentalCarResponseDto);
    }

    @Test
    void shouldMapToDtoList() {
        // TODO
        List<RentalCarEntity> rentalCarEntities = rentalCarEntities();
        RentalCarDtoMapper rentalCarDtoMapper = new RentalCarDtoMapper();

        List<RentalCarResponseDto> rentalCarResponseList = rentalCarDtoMapper.mapToDtoList(rentalCarEntities);

        assertThat(rentalCarResponseList).isNotNull()
                .hasSize(1)
                .extracting( "brand",
                            "model",
                            "rentAmount",
                            "securityDepositAmount",
                            "numberOfSeats",
                            "numberOfDoors",
                            "hasAirConditioning")
                .containsExactlyInAnyOrder(
                        tuple("Audi",
                                "A3",
                                3000.0,
                                1000.0,
                                5,
                                5,
                                true)
                );
    }


    // TODO a refaire
    @Test
    void shouldMapToEntity() {
        RentalCarDtoMapper rentalCarDtoMapper = new RentalCarDtoMapper();
        RentalCarEntity expectedRentalCarEntity = oneRentalCarEntity();
        RentalCarEntity rentalCarEntity = rentalCarDtoMapper.mapToEntity(oneRentalCarRequest());

        assertEquals(expectedRentalCarEntity.getBrand(), rentalCarEntity.getBrand());
        assertEquals(expectedRentalCarEntity.getModel(), rentalCarEntity.getModel());
        assertEquals(expectedRentalCarEntity.getRentAmount(), rentalCarEntity.getRentAmount());
        assertEquals(expectedRentalCarEntity.getSecurityDepositAmount(), rentalCarEntity.getSecurityDepositAmount());
        assertEquals(expectedRentalCarEntity.getNumberOfSeats(), rentalCarEntity.getNumberOfSeats());
        assertEquals(expectedRentalCarEntity.getNumberOfDoors(), rentalCarEntity.getNumberOfDoors());
        assertEquals(expectedRentalCarEntity.isHasAirConditioning(), rentalCarEntity.isHasAirConditioning());
    }
}
