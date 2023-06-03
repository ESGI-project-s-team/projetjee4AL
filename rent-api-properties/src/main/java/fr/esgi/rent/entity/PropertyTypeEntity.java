package fr.esgi.rent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "property_type")
public class PropertyTypeEntity {

    public PropertyTypeEntity() {

    }

    public PropertyTypeEntity(String designation) {
        this.designation = designation;
    }

    public PropertyTypeEntity(Integer id, String designation) {
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
