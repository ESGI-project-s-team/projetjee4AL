package fr.esgi.rent.domain;

import fr.esgi.rent.dto.request.RentalCarRequestDto;
import fr.esgi.rent.dto.request.RentalCarRequestPatchDto;
import fr.esgi.rent.dto.response.RentalCarResponseDto;
import fr.esgi.rent.entity.RentalCarEntity;
import fr.esgi.rent.mapper.RentalCarDtoMapper;
import fr.esgi.rent.repository.RentalCarRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static fr.esgi.rent.samples.RentalCarDtoSample.*;
import static fr.esgi.rent.samples.RentalCarEntitySample.oneRentalCarEntity;
import static fr.esgi.rent.samples.RentalCarEntitySample.rentalCarEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
public class RentalCarServiceTest {
    
    @InjectMocks
    private RentalCarService service;
    
    @Mock
    private RentalCarRepository rentalCarRepository;
    
    @Mock
    private RentalCarDtoMapper rentalCarDtoMapper;

    @Test
    void should_return_all_rental_properties() {
        List<RentalCarEntity> rentalCarEntities = rentalCarEntities();
        List<RentalCarResponseDto> rentalCarResponseList = rentalCarResponseList();

        when(rentalCarRepository.findAll()).thenReturn(rentalCarEntities);
        when(rentalCarDtoMapper.mapToDtoList(rentalCarEntities)).thenReturn(rentalCarResponseList);
        val actual = service.getRentalCars();

        assertThat(actual).isNotNull()
                .hasSize(1)
                .extracting("brand", "model", "rentAmount", "securityDepositAmount", "numberOfSeats", "numberOfDoors", "hasAirConditioning")
                .containsExactlyInAnyOrder(
                        tuple("Audi",
                                "A3",
                                3000.0,
                                1000.0,
                                5,
                                5,
                                true)
                );
        verify(rentalCarDtoMapper, times(1)).mapToDtoList(rentalCarEntities);
        verify(rentalCarRepository, times(1)).findAll();
        verifyNoMoreInteractions(rentalCarRepository, rentalCarDtoMapper);
    }

    @Test
    void should_throw_empty_all_rental_property_exception() {
        when(rentalCarRepository.findAll()).thenReturn(List.of());
        assertDoesNotThrow(() -> service.getRentalCars());
        verify(rentalCarRepository, times(1)).findAll();
        verifyNoMoreInteractions(rentalCarRepository);
    }

    @Test
    void should_return_rental_car_by_id() {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();

        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);
        when(rentalCarRepository.findById(1)).thenReturn(java.util.Optional.of(rentalCarEntity));
        val actual = service.getRentalCarById(String.valueOf(1));

        assertThat(actual).isNotNull()
                .extracting("brand", "model", "rentAmount", "securityDepositAmount", "numberOfSeats", "numberOfDoors", "hasAirConditioning")
                .containsExactlyInAnyOrder(
                        "Audi",
                        "A3",
                        3000.0,
                        1000.0,
                        5,
                        5,
                        true
                );
        verify(rentalCarDtoMapper, times(1)).mapToDto(rentalCarEntity);
        verify(rentalCarRepository, times(1)).findById(1);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarDtoMapper);
    }

    @Test
    void should_throw_not_found_rental_property_exception() {
        String id = "1";

        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getRentalCarById(id));

        verify(rentalCarRepository, times(1)).findById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalCarRepository);

    }

    @Test
    void should_create_rental_property() {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarRepository.save(rentalCarEntity)).thenReturn(rentalCarEntity);

        assertDoesNotThrow(() -> service.createRentalCar(rentalCarRequestDto));

        verify(rentalCarRepository, times(1)).save(rentalCarEntity);
        verify(rentalCarDtoMapper, times(1)).mapToEntity(rentalCarRequestDto);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarDtoMapper);
    }

    @Test
    void should_throw_not_found_rental_property_exception_when_create_rental_property() {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarRepository.save(rentalCarEntity)).thenThrow(InstantiationError.class);

        assertThrows(InstantiationError.class, () -> service.createRentalCar(rentalCarRequestDto));

        verify(rentalCarRepository, times(1)).save(rentalCarEntity);
        verify(rentalCarDtoMapper, times(1)).mapToEntity(rentalCarRequestDto);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarDtoMapper);
    }

    @Test
    void should_update_rental_property() {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        String id = "1";

        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalCarEntity));
        when(rentalCarRepository.save(rentalCarEntity)).thenReturn(rentalCarEntity);

        assertDoesNotThrow(() -> service.updateRentalCar(id, rentalCarRequestDto));

        verify(rentalCarRepository, times(1)).findById(Integer.valueOf(id));
        verify(rentalCarRepository, times(1)).save(rentalCarEntity);
        verify(rentalCarDtoMapper, times(1)).mapToEntity(rentalCarRequestDto);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarDtoMapper);
    }

    @Test
    void should_throw_not_found_rental_property_exception_when_update_rental_property() {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        String id = "1";
        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.empty());
        assertDoesNotThrow(() -> service.updateRentalCar(id, rentalCarRequestDto));
        verify(rentalCarRepository, times(1)).findById(Integer.valueOf(id));
        verify(rentalCarRepository, times(1)).save(rentalCarEntity);
        verify(rentalCarDtoMapper, times(1)).mapToEntity(rentalCarRequestDto);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarDtoMapper);
    }

    @Test
    void should_delete_rental_property() {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        String id = "1";

        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalCarEntity));
        doNothing().when(rentalCarRepository).deleteById(Integer.valueOf(id));

        assertDoesNotThrow(() -> service.deleteRentalCar(id));

        verify(rentalCarRepository).findById(Integer.valueOf(id));
        verify(rentalCarRepository).deleteById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalCarRepository);
    }

    @Test
    void should_throw_not_found_rental_property_exception_when_delete_rental_property() {
        String id = "1";

        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.empty());
        assertDoesNotThrow(() -> service.deleteRentalCar(id));

        verify(rentalCarRepository).findById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalCarRepository);
    }

    @Test
    void should_patch_rental_property() {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarRequestPatchDto rentalCarRequestPatchDto = oneRentalCarRequestPatch();
        String id = "1";

        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalCarEntity));
        when(rentalCarRepository.save(rentalCarEntity)).thenReturn(rentalCarEntity);

        assertDoesNotThrow(() -> service.patchRentalCar(id, rentalCarRequestPatchDto));

        verify(rentalCarRepository).findById(Integer.valueOf(id));
        verify(rentalCarRepository).save(rentalCarEntity);
        verifyNoMoreInteractions(rentalCarRepository);
    }

    @Test
    void should_throw_not_found_rental_property_exception_when_patch_rental_property() {
        RentalCarRequestPatchDto rentalCarRequestPatchDto = oneRentalCarRequestPatch();
        String id = "1";

        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.patchRentalCar(id, rentalCarRequestPatchDto));

        verify(rentalCarRepository, times(1)).findById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalCarRepository);
    }
}
