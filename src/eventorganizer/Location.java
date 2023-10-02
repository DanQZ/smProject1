package eventorganizer;

/**
 * Define the abstract data type Location.
 * @KimberlyDonnarumma
 * @DanielZhang
 */
public enum Location {
    HLL114("HLL114", "Hill Center", "Busch"),
    ARC103("ARC103", "Allison Road Classroom", "Busch"),
    BE_AUD("BE_AUD", "Beck Hall", "Livingston"),
    TIL232("TIL232", "Tillett Hall", "Livingston"),
    AB2225("AB2225", "Academic Building", "College Avenue"),
    MU302("MU302", "Murray Hall", "College Avenue");

    private final String roomNumber;
    private final String buildingName;
    private final String campus;

    /**
     * Constructor to create a Location enum.
     * @param roomNumber
     * @param buildingName
     * @param campus
     */
    Location(String roomNumber, String buildingName, String campus) {
        this.roomNumber = roomNumber;
        this.buildingName = buildingName;
        this.campus = campus;
    }

    /**
     * Getter method.
     * @return Room number of the Location.
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Getter method.
     * @return Building name of the Location.
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Getter method.
     * @return Campus of the Location.
     */
    public String getCampus() {
        return campus;
    }
}
