package fr.esgi.mapper;

import com.google.gson.Gson;
import fr.esgi.dto.request.RentalPropertyDtoRequest;
import fr.esgi.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class RentalPropertyMapper {

    public RentalPropertyMapper(){
    }

    public List<RentalPropertyDtoResponse> stringToDtoResponseList(String responseBody){
        return Arrays.stream(new Gson().fromJson(responseBody, RentalPropertyDtoResponse[].class)).toList();
    }

    public RentalPropertyDtoResponse stringToDtoResponse(String responseBody){
        return new Gson().fromJson(responseBody, RentalPropertyDtoResponse.class);
    }

    public String dtoRequestToString(RentalPropertyDtoRequest rentalPropertyDtoRequest){
        return new Gson().toJson(rentalPropertyDtoRequest);
    }

    public String patchDtoRequestToString(RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto){
        return new Gson().toJson(rentalPropertyRequestPatchDto);
    }

}
