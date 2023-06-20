package fr.esgi.rent.mapper;

import fr.esgi.mapper.RentalCarMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.esgi.rent.sample.HttpResponseSample.*;
import static fr.esgi.rent.sample.RentalCarDtoRequestSample.oneRentalCarDtoRequest;
import static fr.esgi.rent.sample.RentalCarDtoRequestSample.oneRentalCarDtoRequestPatch;
import static fr.esgi.rent.sample.RentalCarDtoResponseSample.oneRentalCarDto;
import static fr.esgi.rent.sample.RentalCarDtoResponseSample.rentalCarDtoResponsesList;
import static fr.esgi.rent.sample.RentalCarMapperSample.dtoCarToString;
import static fr.esgi.rent.sample.RentalCarMapperSample.patchDtoCarToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RentalCarMapperTest {

    @InjectMocks
    private RentalCarMapper rentalCarMapper;


    @Test
    public void shouldMapStringToResponseDtoList(){
        assertEquals(rentalCarDtoResponsesList(), rentalCarMapper.stringToDtoResponseList(rentalCarGetAllResponse()));
    }

    @Test
    public void shouldMapStringToResponseDto(){
        assertEquals(oneRentalCarDto(), rentalCarMapper.stringToDtoResponse(rentalCarGetOneResponse()));
    }

    @Test
    public void shouldMapDtoRequestToString(){
        assertEquals(dtoCarToString(), rentalCarMapper.dtoRequestToString(oneRentalCarDtoRequest()));
    }

    @Test
    public void shouldMapPatchDtoRequestToString(){
        assertEquals(patchDtoCarToString(), rentalCarMapper.patchDtoRequestToString(oneRentalCarDtoRequestPatch()));
    }

}
