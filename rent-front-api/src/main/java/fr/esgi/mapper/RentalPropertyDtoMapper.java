package fr.esgi.mapper;

import fr.esgi.beans.RentalProperty;
import fr.esgi.dto.response.RentalPropertyDtoResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RentalPropertyDtoMapper {

    public RentalPropertyDtoMapper(){

    }

    public RentalPropertyDtoResponse mapToDtoResponse(RentalProperty rentalProperty) {
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

}
