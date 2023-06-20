package fr.esgi.mapper;

import com.google.gson.Gson;
import fr.esgi.dto.request.RentalCarDtoRequest;
import fr.esgi.dto.request.RentalCarRequestPatchDto;
import fr.esgi.dto.response.RentalCarDtoResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class RentalCarMapper {


    public RentalCarMapper(){

    }

    public List<RentalCarDtoResponse> stringToDtoResponseList(String responseBody){
        return Arrays.stream(new Gson().fromJson(responseBody, RentalCarDtoResponse[].class)).toList();

    }

    public RentalCarDtoResponse stringToDtoResponse(String responseBody){
        return new Gson().fromJson(responseBody, RentalCarDtoResponse.class);
    }

    public String dtoRequestToString(RentalCarDtoRequest rentalCarDtoRequest){
        return new Gson().toJson(rentalCarDtoRequest);
    }

    public String patchDtoRequestToString(RentalCarRequestPatchDto rentalCarRequestPatchDto){
        return new Gson().toJson(rentalCarRequestPatchDto);
    }

}
