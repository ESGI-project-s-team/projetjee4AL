package fr.esgi.rent.sample;

public class RentalCarMapperSample {

    public static String dtoCarToString(){
        return "{\"brand\":\"BMW\",\"model\":\"Serie 1\",\"rentAmount\":790.9,\"securityDepositAmount\":1550.9,\"numberOfSeats\":5,\"numberOfDoors\":4,\"hasAirConditioning\":true}";
    }
    public static String patchDtoCarToString(){
        return "{\"rentAmount\":790.9}";
    }

}
