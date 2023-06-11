package fr.esgi.rent.mapper;


import org.springframework.stereotype.Component;
import fr.esgi.rent.dto.request.RentalCarRequestDto;
import fr.esgi.rent.dto.response.RentalCarResponseDto;
import fr.esgi.rent.entity.RentalCarEntity;

import java.util.List;

@Component
public class RentalCarDtoMapper {
    public List<RentalCarResponseDto> mapToDtoList(List<RentalCarEntity> rentalCars) {
        return rentalCars.stream()
                .map(this::mapToDto)
                .toList();
    }

    public RentalCarResponseDto mapToDto(RentalCarEntity rentalCar) {
        return new RentalCarResponseDto(
                rentalCar.getBrand(),
                rentalCar.getModel(),
                rentalCar.getRentAmount(),
                rentalCar.getSecurityDepositAmount(),
                rentalCar.getNumberOfSeats(),
                rentalCar.getNumberOfDoors(),
                rentalCar.isHasAirConditioning());
    }

    public RentalCarEntity mapToEntity(RentalCarRequestDto rentalCarRequestDto) {
        return new RentalCarEntity(
                rentalCarRequestDto.brand(),
                rentalCarRequestDto.model(),
                rentalCarRequestDto.rentAmount(),
                rentalCarRequestDto.securityDepositAmount(),
                rentalCarRequestDto.numberOfSeats(),
                rentalCarRequestDto.numberOfDoors(),
                rentalCarRequestDto.hasAirConditioning());
    }
}
