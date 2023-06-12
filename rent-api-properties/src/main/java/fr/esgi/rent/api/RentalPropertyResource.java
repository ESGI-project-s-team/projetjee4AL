package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalPropertyService;
import fr.esgi.rent.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.rent.dto.request.RentalPropertyRequestDto;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/rent-properties-api")
public class RentalPropertyResource {
    private final RentalPropertyService rentalPropertyService;

    public RentalPropertyResource(RentalPropertyRepository rentalPropertyRepository,
                                  RentalPropertyDtoMapper rentalPropertyDtoMapper) {
        this.rentalPropertyService = new RentalPropertyService(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @GetMapping("/rental-properties")
    public List<RentalPropertyResponseDto> getRentalProperties() {
        return rentalPropertyService.getRentalProperties();
    }

    @GetMapping("/rental-properties/{id}")
    public RentalPropertyResponseDto getRentalPropertyById(@PathVariable String id) {
        try {
            return rentalPropertyService.getRentalPropertyById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/rental-properties")
    @ResponseStatus(CREATED)
    public void createRentalProperty(@Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {
        rentalPropertyService.createRentalProperty(rentalPropertyRequestDto);
    }

    @PutMapping("/rental-properties/{id}")
    public void updateRentalProperty(@PathVariable String id, @Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {
        rentalPropertyService.updateRentalProperty(id, rentalPropertyRequestDto);
    }

    @DeleteMapping("/rental-properties/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteRentalProperty(@PathVariable String id) {
        rentalPropertyService.deleteRentalProperty(id);
    }

    @PatchMapping("/rental-properties/{id}")
    public void patchRentalProperty(@PathVariable String id, @Valid @RequestBody RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto) {
        rentalPropertyService.patchRentalProperty(id, rentalPropertyRequestPatchDto);
    }
}
