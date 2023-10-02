package eventorganizer;

/**
 * Define the abstract data type Event.
 * @KimberlyDonnarumma
 * @DanielZhang
 */
public class Event implements Comparable<Event>{
    private Date date;
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;

    /**
     * Default constructor
     */
    public Event(){}

    /**
     * Parameterized constructor with 5 parameters.
     * @param date
     * @param startTime
     * @param location
     * @param contact
     * @param duration
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration){
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Getter method.
     * @return Date of the Event.
     */
    public Date getDate(){
        return date;
    }

    /**
     * Getter method.
     * @return Start time of the Event.
     */
    public Timeslot getStartTime(){
        return startTime;
    }

    /**
     * Getter method.
     * @return Location of the Event.
     */
    public Location getLocation(){
        return location;
    }

    /**
     * Getter method.
     * @return Contact of the Event.
     */
    public Contact getContact(){
        return contact;
    }

    /**
     * Getter method.
     * @return Duration of the Event.
     */
    public int getDuration() { return duration; };

    /**
     * Override.
     * @param event the object to be compared.
     * @return -1 if the object is less than, 1 if the object is greater than, and 0
     * if they are the same.
     */
    @Override
    public int compareTo(Event event){
        if(this.date.compareTo(event.date) > 0){
            return 1;
        }
        if(this.date.compareTo(event.date) < 0){
            return -1;
        }
        if(this.startTime.getHour() < event.startTime.getHour()){
            return -1;
        }
        if(this.startTime.getHour() > event.startTime.getHour()){
            return 1;
        }
        return 0;
    }

    /**
     * Override.
     * @param obj
     * @return 1 if the events are the same, 0 otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Event){
            Event event = (Event) obj;
            if(this.date.equals(event.getDate()) &&
                this.startTime.equals(event.startTime) &&
                this.location.equals(event.location)){
                return true;
            }
        }
        return false;
    }

    /**
     * Override.
     * @return Event in the form of a string.
     */
    @Override
    public String toString() {
        String eventOutput = "";
        eventOutput += "[Event Date: "
                + date.toString() + "] [Start: "
                + startTime.getHourStandard() + ":"
                + startTime.getMinuteFirstDigit()
                + startTime.getMinuteSecondDigit()
                + amOrPm(startTime.getHour())
                + "] [" + getEndTime() +
                amOrPm(getEndHour()) +"] "
                + location.getRoomNumber() + " ("
                + location.getBuildingName() + ", "
                + location.getCampus() + ") [Contact: "
                + contact.getDepartment().getFullName()
                + ", " + contact.toString() + "]";
        return eventOutput;
    }

    /**
     * Returns if the given hour (in military time) is in the AM or PM.
     * @param hour
     * @return String "AM" or string "PM".
     */
    private String amOrPm(int hour){
        if(hour >= 12){
            return "pm";
        }

        return "am";
    }
    /**
     * Private helper method to calculate the end time of the event.
     * @return End time of the event in the form of a string.
     */
    private String getEndTime(){
        int changingDuration = duration;
        int endHour = startTime.getHour();
        int endMinute = (startTime.getMinuteFirstDigit() * 10) + startTime.getMinuteSecondDigit();
        String endtime = "";
        while(changingDuration >= 60) {
            endHour++;
            changingDuration -= 60;
        }
        endMinute += changingDuration;
        while(endMinute >= 60){
            endHour++;
            endMinute -= 60;
        }
        int endMinuteFirstDigit;
        int endMinuteSecondDigit;
        endMinuteFirstDigit = endMinute / 10;
        endMinuteSecondDigit = endMinute % 10;
        if(endHour > 12){
            endHour = endHour - 12;
        }
        endtime += endHour + ":" + endMinuteFirstDigit + endMinuteSecondDigit;
        return endtime;
    }

    /**
     * Calculates the end hour of an event.
     * @return The end hour integer.
     */
    private int getEndHour(){
        int changingDuration = duration;
        int endHour = startTime.getHour();
        int endMinute = (startTime.getMinuteFirstDigit() * 10) + startTime.getMinuteSecondDigit();
        while(changingDuration >= 60) {
            endHour++;
            changingDuration -= 60;
        }
        endMinute += changingDuration;
        while(endMinute >= 60){
            endHour++;
            endMinute -= 60;
        }

        return endHour;
    }

    /**
     * Testbed main to test the equals() method.
     * @param args
     */
    public static void main(String[] args) {
        TestDiffTimeslot(); // test 1
        TestDiffDate(); // test 2
        TestDiffLocation(); // test 3
        TestDiffExtraneous(); // test 4
        TestDifferentEverything(); // test 5
    }
    private static void TestDiffTimeslot(){
        Date newDate1 = new Date(2022,1,20);

        Timeslot newTimeslot1 = Timeslot.AFTERNOON;
        Location newLocation1 = Location.HLL114;
        Department newDepartment1 = Department.CS;
        String newEmail1 = "cs@rutgers.edu";
        int newDuration1 = 60;

        Contact newContact1 = new Contact(newDepartment1, newEmail1);
        Event tempEvent1 = new Event(
                newDate1,
                newTimeslot1,
                newLocation1,
                newContact1,
                newDuration1
        );

        Date newDate2 = new Date(2022,1,20);

        Timeslot newTimeslot2 = Timeslot.MORNING;
        Location newLocation2 = Location.HLL114;
        Department newDepartment2 = Department.CS;
        String newEmail2 = "cs@rutgers.edu";
        int newDuration2 = 60;

        Contact newContact2 = new Contact(newDepartment2, newEmail2);
        Event tempEvent2 = new Event(
                newDate2,
                newTimeslot2,
                newLocation2,
                newContact2,
                newDuration2
        );

        boolean output = tempEvent1.equals(tempEvent2);
        System.out.println("Testing equals() with different timeslots, should output FALSE : " + output);
    }
    private static void TestDiffDate(){

        Date newDate1 = new Date(2022,2,20);

        Timeslot newTimeslot1 = Timeslot.AFTERNOON;
        Location newLocation1 = Location.HLL114;
        Department newDepartment1 = Department.CS;
        String newEmail1 = "cs@rutgers.edu";
        int newDuration1 = 60;

        Contact newContact1 = new Contact(newDepartment1, newEmail1);
        Event tempEvent1 = new Event(
                newDate1,
                newTimeslot1,
                newLocation1,
                newContact1,
                newDuration1
        );

        Date newDate2 = new Date(2022,1,20);

        Timeslot newTimeslot2 = Timeslot.AFTERNOON;
        Location newLocation2 = Location.HLL114;
        Department newDepartment2 = Department.CS;
        String newEmail2 = "cs@rutgers.edu";
        int newDuration2 = 60;

        Contact newContact2 = new Contact(newDepartment2, newEmail2);
        Event tempEvent2 = new Event(
                newDate2,
                newTimeslot2,
                newLocation2,
                newContact2,
                newDuration2
        );

        boolean output = tempEvent1.equals(tempEvent2);
        System.out.println("Testing equals() with different dates, should output FALSE : " + output);
    }
    private static void TestDiffLocation(){

        Date newDate1 = new Date(2022,1,20);

        Timeslot newTimeslot1 = Timeslot.AFTERNOON;
        Location newLocation1 = Location.HLL114;
        Department newDepartment1 = Department.CS;
        String newEmail1 = "cs@rutgers.edu";
        int newDuration1 = 60;

        Contact newContact1 = new Contact(newDepartment1, newEmail1);
        Event tempEvent1 = new Event(
                newDate1,
                newTimeslot1,
                newLocation1,
                newContact1,
                newDuration1
        );

        Date newDate2 = new Date(2022,1,20);

        Timeslot newTimeslot2 = Timeslot.AFTERNOON;
        Location newLocation2 = Location.ARC103;
        Department newDepartment2 = Department.CS;
        String newEmail2 = "cs@rutgers.edu";
        int newDuration2 = 60;

        Contact newContact2 = new Contact(newDepartment2, newEmail2);
        Event tempEvent2 = new Event(
                newDate2,
                newTimeslot2,
                newLocation2,
                newContact2,
                newDuration2
        );

        boolean output = tempEvent1.equals(tempEvent2);
        System.out.println("Testing equals() with different locations, should output FALSE : " + output);
    }
    private static void TestDiffExtraneous(){

        Date newDate1 = new Date(2022,1,20);

        Timeslot newTimeslot1 = Timeslot.AFTERNOON;
        Location newLocation1 = Location.HLL114;
        Department newDepartment1 = Department.EE;
        String newEmail1 = "ee@rutgers.edu";
        int newDuration1 = 60;

        Contact newContact1 = new Contact(newDepartment1, newEmail1);
        Event tempEvent1 = new Event(
                newDate1,
                newTimeslot1,
                newLocation1,
                newContact1,
                newDuration1
        );

        Date newDate2 = new Date(2022,1,20);

        Timeslot newTimeslot2 = Timeslot.MORNING;
        Location newLocation2 = Location.HLL114;
        Department newDepartment2 = Department.CS;
        String newEmail2 = "cs@rutgers.edu";
        int newDuration2 = 45;

        Contact newContact2 = new Contact(newDepartment2, newEmail2);
        Event tempEvent2 = new Event(
                newDate2,
                newTimeslot2,
                newLocation2,
                newContact2,
                newDuration2
        );

        boolean output = tempEvent1.equals(tempEvent2);
        System.out.println("Testing equals() with different extraneous attributes, should output TRUE : " + output);
    }
    private static void TestDifferentEverything(){

        Date newDate1 = new Date(2022,2,20);

        Timeslot newTimeslot1 = Timeslot.AFTERNOON;
        Location newLocation1 = Location.ARC103;
        Department newDepartment1 = Department.CS;
        String newEmail1 = "cs@rutgers.edu";
        int newDuration1 = 60;

        Contact newContact1 = new Contact(newDepartment1, newEmail1);
        Event tempEvent1 = new Event(
                newDate1,
                newTimeslot1,
                newLocation1,
                newContact1,
                newDuration1
        );

        Date newDate2 = new Date(2022,1,20);

        Timeslot newTimeslot2 = Timeslot.MORNING;
        Location newLocation2 = Location.HLL114;
        Department newDepartment2 = Department.CS;
        String newEmail2 = "cs@rutgers.edu";
        int newDuration2 = 60;

        Contact newContact2 = new Contact(newDepartment2, newEmail2);
        Event tempEvent2 = new Event(
                newDate2,
                newTimeslot2,
                newLocation2,
                newContact2,
                newDuration2
        );

        boolean output = tempEvent1.equals(tempEvent2);
        System.out.println("Testing equals() with different location, timeslot, and date, should output FALSE : " + output);
    }
}
