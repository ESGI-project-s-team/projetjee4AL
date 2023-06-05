package fr.esgi.beans;

public record RentalCar(
        int id,
        String brand,
        String model,
        double rent_amount,
        double securityDepositAmount,
        int numberOfSeats,
        int numberOfDoors,
        boolean hasAirConditioning
) {
}
