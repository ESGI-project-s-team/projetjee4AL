package fr.esgi.rent.repository;

import fr.esgi.rent.entity.RentalCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalCarRepository extends JpaRepository<RentalCarEntity, Integer> {

}
