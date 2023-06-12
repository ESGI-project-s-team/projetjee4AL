package fr.esgi.rent.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Builder
@Table(name = "rental_car")
public class RentalCarEntity {

    public RentalCarEntity() {

    }

    public RentalCarEntity(String brand,
                           String model,
                           double rentAmount,
                           double securityDepositAmount,
                           int numberOfSeats,
                           int numberOfDoors,
                           boolean hasAirConditioning) {
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
    }

    public RentalCarEntity(Integer id,
                           String brand,
                           String model,
                           double rentAmount,
                           double securityDepositAmount,
                           int numberOfSeats,
                           int numberOfDoors,
                           boolean hasAirConditioning) {
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
    }

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "rent_amount")
    private double rentAmount;

    @Column(name = "security_deposit_amount")
    private double securityDepositAmount;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "number_of_doors")
    private int numberOfDoors;

    @Column(name = "has_air_conditioning")
    private boolean hasAirConditioning;
}
