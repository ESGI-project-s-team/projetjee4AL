package fr.esgi.rent.sample;

import fr.esgi.dto.response.RentalPropertyDtoResponse;

import java.net.http.HttpResponse;
import java.util.List;

import static org.mockito.Mockito.mock;

public class HttpResponseSample {
    public static String rentalPropertyGetAllResponse(){
        return "[{\"address\": \"77 Rue des roses\",\"area\": 37.48,\"description\": \"Appartement spacieux avec vue sur l'ESGI\",\"propertyType\": \"FLAT\",\"rentAmount\": 750.9,\"securityDepositAmount\": 1200.9,\"town\": \"Paris\"},{\"address\": \"12 rue de la Pyramide\",\"area\": 62.5,\"description\": \"Maison à louer dans banlieue calme et proche du RER\",\"propertyType\": \"HOUSE\",\"rentAmount\": 1050.90,\"securityDepositAmount\": 1400.90,\"town\": \"Champs-sur-Marne\"}]";
    }

    public static String rentalPropertyGetOneResponse(){
        return "{\"address\": \"77 Rue des roses\",\"area\": 37.48,\"description\": \"Appartement spacieux avec vue sur l'ESGI\",\"propertyType\": \"FLAT\",\"rentAmount\": 750.9,\"securityDepositAmount\": 1200.9,\"town\": \"Paris\"}";
    }

    public static String rentalCarGetAllResponse(){
        return "[{\"brand\": \"BMW\",\"model\" : \"Serie 1\",\"rentAmount\" : 790.9,\"securityDepositAmount\" : 1550.9,\"numberOfSeats\" : 5,\"numberOfDoors\": 4,\"hasAirConditioning\" : \"true\"},{\"brand\": \"Mercedes\",\"model\" : \"Classe C Hybride\",\"rentAmount\" : 990.9,\"securityDepositAmount\" : 2400.9,\"numberOfSeats\" : 5,\"numberOfDoors\": 4,\"hasAirConditioning\" : \"true\"}]";
    }

    public static String rentalCarGetOneResponse(){
        return "{\"brand\": \"BMW\",\"model\" : \"Serie 1\",\"rentAmount\" : 790.9,\"securityDepositAmount\" : 1550.9,\"numberOfSeats\" : 5,\"numberOfDoors\": 4,\"hasAirConditioning\" : \"true\"}";
    }

}
