package fr.esgi.rent.api;

import fr.esgi.rent.entity.RentalPropertyEntity;
import fr.esgi.rent.dto.request.RentalPropertyRequestDto;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/rent-properties-api")
public class RentalPropertyResource {

    private final RentalPropertyRepository rentalPropertyRepository;
    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;

    public RentalPropertyResource(RentalPropertyRepository rentalPropertyRepository,
                                  RentalPropertyDtoMapper rentalPropertyDtoMapper) {
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.rentalPropertyDtoMapper = rentalPropertyDtoMapper;
    }

    @GetMapping("/rental-properties")
    public List<RentalPropertyResponseDto> getRentalProperties() {
        List<RentalPropertyEntity> rentalProperties = rentalPropertyRepository.findAll();

        return rentalPropertyDtoMapper.mapToDtoList(rentalProperties);
    }

    @GetMapping("/rental-properties/{id}")
    public RentalPropertyResponseDto getRentalPropertyById(@PathVariable String id) {
        Optional<RentalPropertyResponseDto> optRentalPropertyDtoResponse = rentalPropertyRepository.findById(UUID.fromString(id))
                .map(rentalPropertyDtoMapper::mapToDto);

        if (optRentalPropertyDtoResponse.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
        return optRentalPropertyDtoResponse.get();

    }

    @PostMapping("/rental-properties")
    @ResponseStatus(CREATED)
    public void createRentalProperty(@Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto);
        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    @PutMapping("/rental-properties/{id}")
    public void updateRentalProperty(@PathVariable String id, @Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {
        Optional<RentalPropertyEntity> optRentalPropertyEntity = rentalPropertyRepository.findById(UUID.fromString(id));
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto);
        if (optRentalPropertyEntity.isPresent()) {
            rentalPropertyEntity.setId(UUID.fromString(id));
        }
        rentalPropertyRepository.save(rentalPropertyEntity);
    }

}
