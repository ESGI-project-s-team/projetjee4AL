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

    public RentalProperty(int id, String description, String town, String address, PropertyType propertyType, double rentAmount, double securityDepositAmount, double area,
                          Integer bedroomsCount, Integer floorNumber, Integer numberOfFloors, String constructionYear, EnergyClassification energyClassification, Boolean hasElevator, Boolean hasIntercom, Boolean hasBalcony, Boolean hasParkingSpace) {
        this.id = id;
        this.description = description;
        this.town = town;
        this.address = address;
        this.propertyType = propertyType;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.area = area;

        this.bedroomsCount = bedroomsCount;
        this.floorNumber = floorNumber;
        this.numberOfFloors = numberOfFloors;
        this.constructionYear = constructionYear;
        this.energyClassification = energyClassification;
        this.hasElevator = hasElevator;
        this.hasIntercom = hasIntercom;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;

    }

}
