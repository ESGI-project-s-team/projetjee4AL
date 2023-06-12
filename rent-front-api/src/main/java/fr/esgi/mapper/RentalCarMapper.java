package fr.esgi.mapper;

import com.google.gson.Gson;
import fr.esgi.dto.request.RentalCarDtoRequest;
import fr.esgi.dto.request.RentalCarRequestPatchDto;
import fr.esgi.dto.request.RentalPropertyDtoRequest;
import fr.esgi.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.dto.response.RentalCarDtoResponse;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class RentalCarMapper {

    private final Gson gson;

    public RentalCarMapper(){
        this.gson = new Gson();
    }

    public List<RentalCarDtoResponse> stringToDtoResponseList(String responseBody){
        return Arrays.stream(gson.fromJson(responseBody, RentalCarDtoResponse[].class)).toList();

    }

    public RentalCarDtoResponse stringToDtoResponse(String responseBody){
        return gson.fromJson(responseBody, RentalCarDtoResponse.class);
    }

    public String dtoRequestToString(RentalCarDtoRequest rentalCarDtoRequest){
        return gson.toJson(rentalCarDtoRequest);
    }

    public String patchDtoRequestToString(RentalCarRequestPatchDto rentalCarRequestPatchDto){
        return gson.toJson(rentalCarRequestPatchDto);
    }

}
