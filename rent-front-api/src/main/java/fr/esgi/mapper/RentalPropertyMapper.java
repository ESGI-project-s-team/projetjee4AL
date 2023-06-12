package fr.esgi.mapper;

import com.google.gson.Gson;
import fr.esgi.dto.request.RentalCarRequestPatchDto;
import fr.esgi.dto.request.RentalPropertyDtoRequest;
import fr.esgi.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class RentalPropertyMapper {

    private final Gson gson;

    public RentalPropertyMapper(){
        this.gson = new Gson();
    }

    public List<RentalPropertyDtoResponse> stringToDtoResponseList(String responseBody){
        return Arrays.stream(gson.fromJson(responseBody, RentalPropertyDtoResponse[].class)).toList();

    }

    public RentalPropertyDtoResponse stringToDtoResponse(String responseBody){
        return gson.fromJson(responseBody, RentalPropertyDtoResponse.class);
    }

    public String dtoRequestToString(RentalPropertyDtoRequest rentalPropertyDtoRequest){
        return gson.toJson(rentalPropertyDtoRequest);
    }

    public String patchDtoRequestToString(RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto){
        return gson.toJson(rentalPropertyRequestPatchDto);
    }

}
