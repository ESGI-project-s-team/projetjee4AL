package fr.esgi.rent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "energy_classification")
public class EnergyClassificationEntity {

    public EnergyClassificationEntity() {

    }

    public EnergyClassificationEntity(String designation) {
        this.designation = designation;
    }

    public EnergyClassificationEntity(Integer id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "designation")
    private String designation;
}
