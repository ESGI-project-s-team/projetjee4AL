package fr.esgi.rent.mapper;

import fr.esgi.mapper.RentalPropertyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.esgi.rent.sample.HttpResponseSample.rentalPropertyGetAllResponse;
import static fr.esgi.rent.sample.HttpResponseSample.rentalPropertyGetOneResponse;
import static fr.esgi.rent.sample.RentalPropertyDtoRequestSample.oneRentalPropertyDtoRequest;
import static fr.esgi.rent.sample.RentalPropertyDtoRequestSample.oneRentalPropertyDtoRequestPatch;
import static fr.esgi.rent.sample.RentalPropertyDtoResponseSample.oneRentalPropertyDto;
import static fr.esgi.rent.sample.RentalPropertyDtoResponseSample.rentalPropertyDtoResponsesList;
import static fr.esgi.rent.sample.RentalPropertyMapperSample.dtoPropertyToString;
import static fr.esgi.rent.sample.RentalPropertyMapperSample.patchDtoPropertyToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RentalPropertyMapperTest {

    @InjectMocks
    private RentalPropertyMapper rentalPropertyMapper;


    @Test
    public void shouldMapStringToResponseDtoList(){
        assertEquals(rentalPropertyDtoResponsesList(), rentalPropertyMapper.stringToDtoResponseList(rentalPropertyGetAllResponse()));
    }

    @Test
    public void shouldMapStringToResponseDto(){
        assertEquals(oneRentalPropertyDto(), rentalPropertyMapper.stringToDtoResponse(rentalPropertyGetOneResponse()));
    }

    @Test
    public void shouldMapDtoRequestToString(){
        assertEquals(dtoPropertyToString(), rentalPropertyMapper.dtoRequestToString(oneRentalPropertyDtoRequest()));
    }

    @Test
    public void shouldMapPatchDtoRequestToString(){
        String test = rentalPropertyMapper.patchDtoRequestToString(oneRentalPropertyDtoRequestPatch());
        assertEquals(patchDtoPropertyToString(), test);
    }

}
