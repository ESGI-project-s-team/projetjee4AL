package fr.esgi.rent.domain;

import fr.esgi.rent.dto.request.RentalPropertyRequestDto;
import fr.esgi.rent.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.entity.RentalPropertyEntity;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static fr.esgi.rent.samples.RentalPropertyDtoSample.*;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.oneRentalPropertyEntity;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(RentalPropertyService.class)
public class RentalPropertiesServiceTest {
    @InjectMocks
    private RentalPropertyService service;
    @Mock
    private RentalPropertyRepository rentalPropertyRepository;
    @Mock
    private RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @Test
    void should_return_all_rental_properties() {
        List<RentalPropertyEntity> rentalPropertyEntities = rentalPropertyEntities();
        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyResponseList();

        when(rentalPropertyRepository.findAll()).thenReturn(rentalPropertyEntities);
        when(rentalPropertyDtoMapper.mapToDtoList(rentalPropertyEntities)).thenReturn(rentalPropertyResponseList);
        val actual = service.getRentalProperties();

        assertThat(actual).isNotNull()
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
        verify(rentalPropertyDtoMapper, times(1)).mapToDtoList(rentalPropertyEntities);
        verify(rentalPropertyRepository, times(1)).findAll();
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }
    @Test
    void should_throw_empty_all_rental_property_exception() {
        when(rentalPropertyRepository.findAll()).thenReturn(List.of());
        assertDoesNotThrow(() -> service.getRentalProperties());
        verify(rentalPropertyRepository, times(1)).findAll();
        verifyNoMoreInteractions(rentalPropertyRepository);
    }
    @Test
    void should_return_rental_property_by_id() {
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyResponseDto rentalPropertyResponseDto = oneRentalPropertyResponse();

        String id = "1";

        when(rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity)).thenReturn(rentalPropertyResponseDto);
        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalPropertyEntity));
        val actual = service.getRentalPropertyById(id);
        assertThat(actual).isNotNull()
                .extracting("description", "address", "town", "propertyType", "rentAmount", "securityDepositAmount", "area")
                .containsExactlyInAnyOrder(
                        "Appartement spacieux avec vue sur l'ESGI",
                        "77 Rue des roses",
                        "Paris",
                        "Appartement",
                        750.90,
                        1200.90,
                        37.48
                );
        verify(rentalPropertyDtoMapper, times(1)).mapToDto(rentalPropertyEntity);
        verify(rentalPropertyRepository, times(1)).findById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void should_throw_not_found_rental_property_exception() {
        String id = "1";

        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getRentalPropertyById(id));

        verify(rentalPropertyRepository, times(1)).findById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalPropertyRepository);

    }

    @Test
    void should_create_rental_property() {
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();

        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyRepository.save(rentalPropertyEntity)).thenReturn(rentalPropertyEntity);

        assertDoesNotThrow(() -> service.createRentalProperty(rentalPropertyRequestDto));

        verify(rentalPropertyRepository, times(1)).save(rentalPropertyEntity);
        verify(rentalPropertyDtoMapper, times(1)).mapToEntity(rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void should_throw_not_found_rental_property_exception_when_create_rental_property() {
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        rentalPropertyEntity.setDescription(null);

        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyRepository.save(rentalPropertyEntity)).thenThrow(InstantiationError.class);

        assertThrows(InstantiationError.class, () -> service.createRentalProperty(rentalPropertyRequestDto));

        verify(rentalPropertyRepository, times(1)).save(rentalPropertyEntity);
        verify(rentalPropertyDtoMapper, times(1)).mapToEntity(rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void should_update_rental_property() {
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();

        String id = "1";

        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalPropertyEntity));
        when(rentalPropertyRepository.save(rentalPropertyEntity)).thenReturn(rentalPropertyEntity);

        assertDoesNotThrow(() -> service.updateRentalProperty(id, rentalPropertyRequestDto));

        verify(rentalPropertyRepository, times(1)).findById(Integer.valueOf(id));
        verify(rentalPropertyRepository, times(1)).save(rentalPropertyEntity);
        verify(rentalPropertyDtoMapper, times(1)).mapToEntity(rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void should_throw_not_found_rental_property_exception_when_update_rental_property() {
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        String id = "1";
        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.empty());
        assertDoesNotThrow(() -> service.updateRentalProperty(id, rentalPropertyRequestDto));
        verify(rentalPropertyRepository, times(1)).findById(Integer.valueOf(id));
        verify(rentalPropertyRepository, times(1)).save(rentalPropertyEntity);
        verify(rentalPropertyDtoMapper, times(1)).mapToEntity(rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void should_delete_rental_property() {
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        String id = "1";

        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalPropertyEntity));
        doNothing().when(rentalPropertyRepository).deleteById(Integer.valueOf(id));

        assertDoesNotThrow(() -> service.deleteRentalProperty(id));

        verify(rentalPropertyRepository).findById(Integer.valueOf(id));
        verify(rentalPropertyRepository).deleteById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    void should_throw_not_found_rental_property_exception_when_delete_rental_property() {
        String id = "1";

        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.empty());
        assertDoesNotThrow(() -> service.deleteRentalProperty(id));

        verify(rentalPropertyRepository).findById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    void should_patch_rental_property() {
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto = oneRentalPropertyRequestPatch();
        String id = "1";

        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalPropertyEntity));
        when(rentalPropertyRepository.save(rentalPropertyEntity)).thenReturn(rentalPropertyEntity);

        assertDoesNotThrow(() -> service.patchRentalProperty(id, rentalPropertyRequestPatchDto));

        verify(rentalPropertyRepository).findById(Integer.valueOf(id));
        verify(rentalPropertyRepository).save(rentalPropertyEntity);
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    void should_throw_not_found_rental_property_exception_when_patch_rental_property() {
        RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto = oneRentalPropertyRequestPatch();
        String id = "1";

        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.patchRentalProperty(id, rentalPropertyRequestPatchDto));

        verify(rentalPropertyRepository, times(1)).findById(Integer.valueOf(id));
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

}
