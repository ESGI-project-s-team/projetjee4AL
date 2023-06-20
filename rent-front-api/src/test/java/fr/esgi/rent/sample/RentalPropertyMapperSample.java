package fr.esgi.rent.sample;

public class RentalPropertyMapperSample {

    public static String dtoPropertyToString(){
        return "{\"address\":\"77 Rue des roses\",\"area\":37.48,\"description\":\"Appartement spacieux avec vue sur l\\u0027ESGI\",\"propertyType\":\"FLAT\",\"rentAmount\":750.9,\"securityDepositAmount\":1200.9,\"town\":\"Paris\"}";
    }
    public static String patchDtoPropertyToString(){
        return "{\"rentAmount\":750.9}";
    }

}
