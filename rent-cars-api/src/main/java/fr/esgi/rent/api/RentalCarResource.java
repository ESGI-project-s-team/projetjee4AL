package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalCarService;
import fr.esgi.rent.dto.request.RentalCarRequestDto;
import fr.esgi.rent.dto.request.RentalCarRequestPatchDto;
import fr.esgi.rent.dto.response.RentalCarResponseDto;
import fr.esgi.rent.mapper.RentalCarDtoMapper;
import fr.esgi.rent.repository.RentalCarRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/rent-cars-api")
public class RentalCarResource {

    private final RentalCarService rentalCarService;

    public RentalCarResource(RentalCarRepository rentalCarRepository,
                             RentalCarDtoMapper rentalCarDtoMapper) {
        this.rentalCarService = new RentalCarService(rentalCarRepository, rentalCarDtoMapper);
    }

    @GetMapping("/rental-cars")
    public List<RentalCarResponseDto> getRentalCars() { return rentalCarService.getRentalCars(); }

    @GetMapping("/rental-cars/{id}")
    public RentalCarResponseDto getRentalCarById(@PathVariable String id) {
        try {
            return rentalCarService.getRentalCarById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/rental-cars")
    @ResponseStatus(CREATED)
    public void createRentalCar(@Valid @RequestBody RentalCarRequestDto rentalCarRequestDto) {
        rentalCarService.createRentalCar(rentalCarRequestDto);
    }

    @PutMapping("/rental-cars/{id}")
    public void updateRentalCar(@PathVariable String id, @Valid @RequestBody RentalCarRequestDto rentalCarRequestDto) {
        rentalCarService.updateRentalCar(id, rentalCarRequestDto);
    }

    @PatchMapping("/rental-cars/{id}")
    public void patchRentalCar(@PathVariable String id, @Valid @RequestBody RentalCarRequestPatchDto rentalCarRequestPatchDto) {
        rentalCarService.patchRentalCar(id, rentalCarRequestPatchDto);
    }

    @DeleteMapping("/rental-cars/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteRentalCar(@PathVariable String id) { rentalCarService.deleteRentalCar(id); }
}