package fr.esgi.rent.api;

import fr.esgi.rent.dto.request.RentalCarRequestDto;
import fr.esgi.rent.dto.request.RentalCarRequestPatchDto;
import fr.esgi.rent.dto.response.RentalCarResponseDto;
import fr.esgi.rent.entity.RentalCarEntity;
import fr.esgi.rent.mapper.RentalCarDtoMapper;
import fr.esgi.rent.repository.RentalCarRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/rent-cars-api")
public class RentalCarResource {

    private final RentalCarRepository rentalCarRepository;
    private final RentalCarDtoMapper rentalCarDtoMapper;

    public RentalCarResource(RentalCarRepository rentalCarRepository,
                             RentalCarDtoMapper rentalCarDtoMapper) {
        this.rentalCarRepository = rentalCarRepository;
        this.rentalCarDtoMapper = rentalCarDtoMapper;
    }

    @GetMapping("/rental-cars")
    public List<RentalCarResponseDto> getRentalCars() {
        List<RentalCarEntity> rentalcars = rentalCarRepository.findAll();

        return rentalCarDtoMapper.mapToDtoList(rentalcars);
    }

    @GetMapping("/rental-cars/{id}")
    public RentalCarResponseDto getRentalCarById(@PathVariable String id) {
        Optional<RentalCarResponseDto> optRentalCarDtoResponse = rentalCarRepository.findById(Integer.parseInt(id))
                .map(rentalCarDtoMapper::mapToDto);

        if (optRentalCarDtoResponse.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
        return optRentalCarDtoResponse.get();
    }

    @PostMapping("/rental-cars")
    @ResponseStatus(CREATED)
    public void createRentalCar(@Valid @RequestBody RentalCarRequestDto rentalCarRequestDto) {
        RentalCarEntity rentalCarEntity = rentalCarDtoMapper.mapToEntity(rentalCarRequestDto);
        rentalCarRepository.save(rentalCarEntity);
    }

    @PutMapping("/rental-cars/{id}")
    public void updateRentalCar(@PathVariable String id, @Valid @RequestBody RentalCarRequestDto rentalCarRequestDto) {
        Optional<RentalCarEntity> optRentalCarEntity = rentalCarRepository.findById(Integer.parseInt(id));
        RentalCarEntity rentalCarEntity = rentalCarDtoMapper.mapToEntity(rentalCarRequestDto);
        if (optRentalCarEntity.isPresent()) {
            rentalCarEntity.setId(Integer.parseInt(id));
        }
        rentalCarRepository.save(rentalCarEntity);
    }

    @PatchMapping("/rental-cars/{id}")
    public void patchRentalCar(@PathVariable String id, @Valid @RequestBody RentalCarRequestPatchDto rentalCarRequestPatchDto) {
        Optional<RentalCarEntity> optRentalCarEntity = rentalCarRepository.findById(Integer.parseInt(id));
        if (optRentalCarEntity.isPresent()) {
            RentalCarEntity rentalCarEntity = optRentalCarEntity.get();
            rentalCarEntity.setRentAmount(rentalCarRequestPatchDto.rentAmount());
            rentalCarRepository.save(rentalCarEntity);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/rental-cars/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteRentalCar(@PathVariable String id) {
        Optional<RentalCarEntity> optRentalCarEntity = rentalCarRepository.findById(Integer.parseInt(id));
        if (optRentalCarEntity.isPresent()) {
            rentalCarRepository.deleteById(Integer.parseInt(id));

        }
    }
}