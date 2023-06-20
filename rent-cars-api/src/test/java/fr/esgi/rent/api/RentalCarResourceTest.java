package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalCarService;
import fr.esgi.rent.dto.request.RentalCarRequestDto;
import fr.esgi.rent.dto.request.RentalCarRequestPatchDto;
import fr.esgi.rent.dto.response.RentalCarResponseDto;
import fr.esgi.rent.entity.RentalCarEntity;
import fr.esgi.rent.mapper.RentalCarDtoMapper;
import fr.esgi.rent.repository.RentalCarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;

import static fr.esgi.rent.samples.RentalCarDtoSample.*;
import static fr.esgi.rent.samples.RentalCarEntitySample.oneRentalCarEntity;
import static fr.esgi.rent.samples.RentalCarEntitySample.rentalCarEntities;
import static fr.esgi.rent.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RentalCarResource.class)
public class RentalCarResourceTest {

    @Value("classpath:/json/rentalCars.json")
    private Resource rentalCars;

    @Value("classpath:/json/rentalCar.json")
    private Resource rentalCar;

    @Value("classpath:/json/rentalCarRequest.json")
    private Resource rentalCarRequest;

    @Value("classpath:/json/invalidRentalCarRequest.json")
    private Resource invalidRentalCarRequest;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalCarRepository rentalCarRepository;
    @MockBean
    private RentalCarService rentalCarService;

    @MockBean
    private RentalCarDtoMapper rentalCarDtoMapper;

    @Test
    @ResponseStatus(HttpStatus.OK)
    void shouldGetRentalCars() throws Exception {
        List<RentalCarEntity> rentalCarEntities = rentalCarEntities();
        List<RentalCarResponseDto> rentalCarResponseList = rentalCarResponseList();

        when(rentalCarDtoMapper.mapToDtoList(rentalCarEntities)).thenReturn(rentalCarResponseList);
        when(rentalCarService.getRentalCars()).thenReturn(rentalCarResponseList);
        when(rentalCarRepository.findAll()).thenReturn(rentalCarEntities);


        mockMvc.perform(get("/rent-cars-api/rental-cars"))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalCars)));

        verify(rentalCarDtoMapper).mapToDtoList(rentalCarEntities);
        verifyNoMoreInteractions(rentalCarService, rentalCarDtoMapper);
    }

    @Test
    void shouldGetRentalCarById() throws Exception {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();

        String id = "1";

        when(rentalCarService.getRentalCarById(id)).thenReturn(rentalCarResponseDto);
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);
        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalCarEntity));

        mockMvc.perform(get("/rent-cars-api/rental-cars/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalCar)));

        verify(rentalCarDtoMapper).mapToDto(rentalCarEntity);
        verifyNoMoreInteractions(rentalCarService, rentalCarDtoMapper);
    }

    @Test
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void givenNoExistentRentalCarId_shouldThrowNotFoundRentalCarException() throws Exception {
        String id = "1";

        when(rentalCarService.getRentalCarById(id)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/rent-cars-api/rental-cars/{id}", id))
                .andExpect(status().isNotFound());

        verifyNoInteractions(rentalCarDtoMapper);
        verifyNoMoreInteractions(rentalCarService);
    }

    @Test
    void shouldCreateRentalCar() throws Exception {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);

        mockMvc.perform(post("/rent-cars-api/rental-cars")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isCreated());

        verify(rentalCarDtoMapper).mapToEntity(rentalCarRequestDto);
        verifyNoMoreInteractions(rentalCarDtoMapper, rentalCarService);
    }


    @Test
    void shouldDeleteRentalCar() throws Exception {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();

        String id = "1";

        when(rentalCarService.getRentalCarById(id)).thenReturn(rentalCarResponseDto);
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);

        mockMvc.perform(delete("/rent-cars-api/rental-cars/{id}", id))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void shouldUpdateRentalCar() throws Exception {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        String id = "1";

        when(rentalCarService.getRentalCarById(id)).thenReturn(rentalCarResponseDto);
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);
        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);


        mockMvc.perform(put("/rent-cars-api/rental-cars/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isOk());

        verify(rentalCarDtoMapper).mapToEntity(rentalCarRequestDto);
    }


    @Test
    void shouldPatchRentalCar() throws Exception {
        RentalCarRequestPatchDto oneRentalCarRequestPatch = oneRentalCarRequestPatch();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        String id = "1";

        when(rentalCarService.getRentalCarById(id)).thenReturn(rentalCarResponseDto);
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);
        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalCarEntity));

        mockMvc.perform(patch("/rent-cars-api/rental-cars/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isOk());

    }

    @Test
    void givenNoExistentRentalCarId_shouldThrowNotFoundRentalCarException_whenPatchRentalCar() throws Exception {
        String id = "1";

        when(rentalCarService.getRentalCarById(id)).thenReturn(null);

        mockMvc.perform(patch("/rent-cars-api/rental-cars/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isNotFound());

        verifyNoMoreInteractions(rentalCarService);
    }

    @Test
    void givenNoExistentRentalCarId_shouldThrowNotFoundRentalCarException_whenUpdatePutRentalCar() throws Exception {
        String id = "1";

        when(rentalCarService.getRentalCarById(id)).thenReturn(null);

        mockMvc.perform(put("/rent-cars-api/rental-cars/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isOk());

        verifyNoMoreInteractions(rentalCarService);
    }
    @Test
    void givenNoExistentRentalCarId_shouldThrowNotFoundRentalCarException_whenDeleteRentalCar() throws Exception {
        String id = "1";

        when(rentalCarService.getRentalCarById(id)).thenReturn(null);

        mockMvc.perform(delete("/rent-cars-api/rental-cars/{id}", id))
                .andExpect(status().is2xxSuccessful());

        verifyNoInteractions(rentalCarDtoMapper);
        verifyNoMoreInteractions(rentalCarService);
    }

    @Test
    void givenInvalidRequestBody_shouldReturn404HttpStatusCode() throws Exception {
        mockMvc.perform(post("/rent-cars-api/rental-cars")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(invalidRentalCarRequest)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(rentalCarDtoMapper, rentalCarService);
    }
}
