package fr.esgi.rent.domain;

import fr.esgi.rent.dto.request.RentalCarRequestDto;
import fr.esgi.rent.dto.request.RentalCarRequestPatchDto;
import fr.esgi.rent.dto.response.RentalCarResponseDto;
import fr.esgi.rent.entity.RentalCarEntity;
import fr.esgi.rent.mapper.RentalCarDtoMapper;
import fr.esgi.rent.repository.RentalCarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class RentalCarService {
    private final RentalCarRepository rentalCarRepository;
    private final RentalCarDtoMapper rentalCarDtoMapper;

    public RentalCarService(RentalCarRepository rentalCarRepository,
                             RentalCarDtoMapper rentalCarDtoMapper) {
        this.rentalCarRepository = rentalCarRepository;
        this.rentalCarDtoMapper = rentalCarDtoMapper;

    }

    public List<RentalCarResponseDto> getRentalCars() {
       List<RentalCarEntity> rentalCars = rentalCarRepository.findAll();
        return rentalCarDtoMapper.mapToDtoList(rentalCars);
    }

    public RentalCarResponseDto getRentalCarById(String id) {
        RentalCarEntity rentalCarEntity = rentalCarRepository.findById(Integer.parseInt(id)).orElseThrow();
        return rentalCarDtoMapper.mapToDto(rentalCarEntity);
    }

    public void createRentalCar(RentalCarRequestDto rentalCarRequestDto) {
        RentalCarEntity rentalCarEntity = rentalCarDtoMapper.mapToEntity(rentalCarRequestDto);
        rentalCarRepository.save(rentalCarEntity);
    }

    public void updateRentalCar(String id, RentalCarRequestDto rentalCarRequestDto) {
        Optional<RentalCarEntity> optRentalCarEntity = rentalCarRepository.findById(Integer.parseInt(id));
        RentalCarEntity rentalCarEntity = rentalCarDtoMapper.mapToEntity(rentalCarRequestDto);
        if (optRentalCarEntity.isPresent()) {
            rentalCarEntity.setId(Integer.parseInt(id));
        }
        rentalCarRepository.save(rentalCarEntity);
    }

    //delete
    public void deleteRentalCar(String id) {
        Optional<RentalCarEntity> optRentalCarEntity = rentalCarRepository.findById(Integer.parseInt(id));
        if (optRentalCarEntity.isPresent()) {
            rentalCarRepository.deleteById(Integer.parseInt(id));
        }
    }

    //patch
    public void patchRentalCar(String id, RentalCarRequestPatchDto rentalCarRequestPatchDto) {
        RentalCarEntity rentalCarEntity = rentalCarRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        rentalCarEntity.setRentAmount(rentalCarRequestPatchDto.rentAmount());
        rentalCarRepository.save(rentalCarEntity);
    }
}
