package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalPropertyService;
import fr.esgi.rent.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.rent.entity.RentalPropertyEntity;
import fr.esgi.rent.dto.request.RentalPropertyRequestDto;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
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

import static fr.esgi.rent.samples.RentalPropertyDtoSample.*;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.oneRentalPropertyEntity;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static fr.esgi.rent.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(RentalPropertyResource.class)
class RentalPropertyResourceTest {

    @Value("classpath:/json/rentalProperties.json")
    private Resource rentalProperties;

    @Value("classpath:/json/rentalProperty.json")
    private Resource rentalProperty;

    @Value("classpath:/json/rentalPropertyRequest.json")
    private Resource rentalPropertyRequest;

    @Value("classpath:/json/invalidRentalPropertyRequest.json")
    private Resource invalidRentalPropertyRequest;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalPropertyRepository rentalPropertyRepository;
    @MockBean
    private RentalPropertyService rentalPropertyService;

    @MockBean
    private RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @Test
    @ResponseStatus(HttpStatus.OK)
    void shouldGetRentalProperties() throws Exception {
        List<RentalPropertyEntity> rentalPropertyEntities = rentalPropertyEntities();
        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyResponseList();

        when(rentalPropertyDtoMapper.mapToDtoList(rentalPropertyEntities)).thenReturn(rentalPropertyResponseList);
        when(rentalPropertyService.getRentalProperties()).thenReturn(rentalPropertyResponseList);
        when(rentalPropertyRepository.findAll()).thenReturn(rentalPropertyEntities);


        mockMvc.perform(get("/rent-properties-api/rental-properties"))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalProperties)));

        verify(rentalPropertyDtoMapper).mapToDtoList(rentalPropertyEntities);
        verifyNoMoreInteractions(rentalPropertyService, rentalPropertyDtoMapper);
    }

    @Test
    void shouldGetRentalPropertyById() throws Exception {
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyResponseDto rentalPropertyResponseDto = oneRentalPropertyResponse();

        String id = "1";

        when(rentalPropertyService.getRentalPropertyById(id)).thenReturn(rentalPropertyResponseDto);
        when(rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity)).thenReturn(rentalPropertyResponseDto);
        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalPropertyEntity));

        mockMvc.perform(get("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalProperty)));

        verify(rentalPropertyDtoMapper).mapToDto(rentalPropertyEntity);
        verifyNoMoreInteractions(rentalPropertyService, rentalPropertyDtoMapper);
    }

    @Test
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void givenNoExistentRentalPropertyId_shouldThrowNotFoundRentalPropertyException() throws Exception {
        String id = "1";

            when(rentalPropertyService.getRentalPropertyById(id)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().isNotFound());

        verifyNoInteractions(rentalPropertyDtoMapper);
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void shouldCreateRentalProperty() throws Exception {
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();
        RentalPropertyResponseDto rentalPropertyResponseDto = oneRentalPropertyResponse();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();

        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity)).thenReturn(rentalPropertyResponseDto);

        mockMvc.perform(post("/rent-properties-api/rental-properties")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalPropertyRequest)))
                .andExpect(status().isCreated());

        verify(rentalPropertyDtoMapper).mapToEntity(rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyDtoMapper, rentalPropertyService);
    }


    @Test
    void shouldDeleteRentalProperty() throws Exception {
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyResponseDto rentalPropertyResponseDto = oneRentalPropertyResponse();

        String id = "1";

        when(rentalPropertyService.getRentalPropertyById(id)).thenReturn(rentalPropertyResponseDto);
        when(rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity)).thenReturn(rentalPropertyResponseDto);

        mockMvc.perform(delete("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void shouldUpdateRentalProperty() throws Exception {
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();
        RentalPropertyResponseDto rentalPropertyResponseDto = oneRentalPropertyResponse();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();

        String id = "1";

        when(rentalPropertyService.getRentalPropertyById(id)).thenReturn(rentalPropertyResponseDto);
        when(rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity)).thenReturn(rentalPropertyResponseDto);
        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(rentalPropertyEntity);


        mockMvc.perform(put("/rent-properties-api/rental-properties/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalPropertyRequest)))
                .andExpect(status().isOk());

        verify(rentalPropertyDtoMapper).mapToEntity(rentalPropertyRequestDto);
    }


    @Test
    void shouldPatchRentalProperty() throws Exception {
        RentalPropertyRequestPatchDto oneRentalPropertyRequestPatch = oneRentalPropertyRequestPatch();
        RentalPropertyResponseDto rentalPropertyResponseDto = oneRentalPropertyResponse();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();

        //create rentalPropertyResponseDto with oneRentalPropertyRequestPatch values and rentalPropertyEntity
        //with oneRentalPropertyRequestPatch values
        //and then compare them


        String id = "1";

        when(rentalPropertyService.getRentalPropertyById(id)).thenReturn(rentalPropertyResponseDto);
        when(rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity)).thenReturn(rentalPropertyResponseDto);
        when(rentalPropertyRepository.findById(Integer.valueOf(id))).thenReturn(java.util.Optional.of(rentalPropertyEntity));

        mockMvc.perform(patch("/rent-properties-api/rental-properties/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalPropertyRequest)))
                .andExpect(status().isOk());

    }

    @Test
    void givenNoExistentRentalPropertyId_shouldThrowNotFoundRentalPropertyException_whenPatchRentalProperty() throws Exception {
        String id = "1";

        when(rentalPropertyService.getRentalPropertyById(id)).thenReturn(null);

        mockMvc.perform(patch("/rent-properties-api/rental-properties/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalPropertyRequest)))
                .andExpect(status().isNotFound());

        verifyNoMoreInteractions(rentalPropertyService);
    }
    @Test
    void givenNoExistentRentalPropertyId_shouldThrowNotFoundRentalPropertyException_whenDeleteRentalProperty() throws Exception {
        String id = "1";

        when(rentalPropertyService.getRentalPropertyById(id)).thenReturn(null);

        mockMvc.perform(delete("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().is2xxSuccessful());

        verifyNoInteractions(rentalPropertyDtoMapper);
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void givenInvalidRequestBody_shouldReturn404HttpStatusCode() throws Exception {
        mockMvc.perform(post("/rent-properties-api/rental-properties")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(invalidRentalPropertyRequest)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(rentalPropertyDtoMapper, rentalPropertyService);
    }

}
