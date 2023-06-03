package fr.esgi.beans;

public record RentalProperty(
        int id,
        String description,
        String town,
        String address,
        PropertyType propertyType,
        double rentAmount,
        double securityDepositAmount,
        double area,
        Integer bedroomsCount,
        Integer floorNumber,
        Integer numberOfFloors,
        String constructionYear,
        EnergyClassification energyClassification,
        Boolean hasElevator,
        Boolean hasIntercom,
        Boolean hasBalcony,
        Boolean hasParkingSpace
) {




}
