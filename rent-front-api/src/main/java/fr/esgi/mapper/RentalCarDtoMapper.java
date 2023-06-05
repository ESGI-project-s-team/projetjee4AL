package fr.esgi.mapper;

import fr.esgi.beans.EnergyClassification;
import fr.esgi.beans.PropertyType;
import fr.esgi.beans.RentalProperty;
import fr.esgi.dto.request.RentalPropertyDtoRequest;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Random;

@ApplicationScoped
public class RentalCarDtoMapper {

    private final Random random;

    public RentalCarDtoMapper() {
        this.random = new Random();

    }

    public List<RentalPropertyDtoResponse> mapToDtoList(List<RentalProperty> rentalProperties) {
        return rentalProperties.stream()
                .map(this::mapToDto)
                .toList();
    }

    public RentalPropertyDtoResponse mapToDto(RentalProperty rentalProperty) {
        return new RentalPropertyDtoResponse(
                rentalProperty.address(),
                rentalProperty.area(),
                rentalProperty.description(),
                rentalProperty.propertyType().designation(),
                rentalProperty.rentAmount(),
                rentalProperty.securityDepositAmount(),
                rentalProperty.town()
        );
    }

    public RentalProperty mapToBean(RentalPropertyDtoRequest rentalPropertyDtoRequest) {
        return new RentalProperty(
                getRandomInt(),
                rentalPropertyDtoRequest.description(),
                rentalPropertyDtoRequest.town(),
                rentalPropertyDtoRequest.address(),
                new PropertyType(getRandomInt(), rentalPropertyDtoRequest.propertyType()),
                rentalPropertyDtoRequest.rentAmount(),
                rentalPropertyDtoRequest.securityDepositAmount(),
                rentalPropertyDtoRequest.area(),
                rentalPropertyDtoRequest.numberOfBedrooms(),
                rentalPropertyDtoRequest.floorNumber(),
                rentalPropertyDtoRequest.numberOfFloors(),
                Integer.toString(rentalPropertyDtoRequest.constructionYear()),
                new EnergyClassification(getRandomInt(), rentalPropertyDtoRequest.energyClassification()),
                rentalPropertyDtoRequest.hasElevator(),
                rentalPropertyDtoRequest.hasIntercom(),
                rentalPropertyDtoRequest.hasBalcony(),
                rentalPropertyDtoRequest.hasParkingSpace()
        );
    }

    private int getRandomInt() {
        return random.nextInt(Integer.MAX_VALUE) + 1;
    }

}
