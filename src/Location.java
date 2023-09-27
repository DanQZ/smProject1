/**
 * Define the abstract data type Location.
 * @
 */
public enum Location {
/*
HLL114, Hill Center, Busch
ARC103, Allison Road Classroom, Busch
BE_AUD, Beck Hall, Livingston
TIL232, Tillett Hall, Livingston
AB2225, Academic Building, College Avenue
MU302, Murray Hall, College Avenue
 */
    HLL114("HLL114", "Hill Center", "Busch"),
    ARC103("ARC103", "Allison Road Classroom", "Busch"),
    BE_AUD("BE_AUD", "Beck Hall", "Livingston"),
    TIL232("TIL232", "Tillett Hall", "Livingston"),
    AB2225("AB2225", "Academic Building", "College Avenue"),
    MU302("MU302", "Murray Hall", "College Avenue");

    private final String roomNumber;
    private final String buildingName;
    private final String campus;

    // Constructor to associate each room with its properties
    Location(String roomNumber, String buildingName, String campus) {
        this.roomNumber = roomNumber;
        this.buildingName = buildingName;
        this.campus = campus;
    }

    // Getter methods to access the properties
    public String getRoomNumber() {
        return roomNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getCampus() {
        return campus;
    }
}
