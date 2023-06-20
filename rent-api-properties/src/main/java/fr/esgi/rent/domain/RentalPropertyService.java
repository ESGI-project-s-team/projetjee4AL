package fr.esgi.rent.domain;

import fr.esgi.rent.dto.request.RentalPropertyRequestDto;
import fr.esgi.rent.dto.request.RentalPropertyRequestPatchDto;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.entity.RentalPropertyEntity;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Slf4j
public class RentalPropertyService {
    private final RentalPropertyRepository rentalPropertyRepository;
    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;

    public RentalPropertyService(RentalPropertyRepository rentalPropertyRepository,
                                 RentalPropertyDtoMapper rentalPropertyDtoMapper) {
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.rentalPropertyDtoMapper = rentalPropertyDtoMapper;
    }

    public List<RentalPropertyResponseDto> getRentalProperties() {
        List<RentalPropertyEntity> rentalProperties = rentalPropertyRepository.findAll();
        return rentalPropertyDtoMapper.mapToDtoList(rentalProperties);
    }

    public RentalPropertyResponseDto getRentalPropertyById(String id) {
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyRepository.findById(Integer.parseInt(id)).orElseThrow();
        return rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity);
    }

    public void createRentalProperty(RentalPropertyRequestDto rentalPropertyRequestDto) {
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto);
        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    public void updateRentalProperty(String id, RentalPropertyRequestDto rentalPropertyRequestDto) {
        Optional<RentalPropertyEntity> optRentalPropertyEntity = rentalPropertyRepository.findById(Integer.parseInt(id));
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto);
        if (optRentalPropertyEntity.isPresent()) {
            rentalPropertyEntity.setId(Integer.parseInt(id));
        }
        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    //delete
    public void deleteRentalProperty(String id) {
        Optional<RentalPropertyEntity> optRentalPropertyEntity = rentalPropertyRepository.findById(Integer.parseInt(id));
        if (optRentalPropertyEntity.isPresent()) {
            rentalPropertyRepository.deleteById(Integer.parseInt(id));
        }
    }

    //patch
    public void patchRentalProperty(String id, RentalPropertyRequestPatchDto rentalPropertyRequestPatchDto) {
        //orElseThrow() CODE STATUS 404
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        rentalPropertyEntity.setRentAmount(rentalPropertyRequestPatchDto.rentAmount());
        rentalPropertyRepository.save(rentalPropertyEntity);
    }
}
