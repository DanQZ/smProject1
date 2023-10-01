/**
 * Define the abstract data type Event.
 * @ KimberlyDonnarumma
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

    public int getDuration() { return duration; };

    /**
     * Testbed.
     * @param args
     */
    public static void main(String[] args){

    }

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
    //[Event Date: 10/21/2023] [Start: 2:00pm] [End: 3:00pm] @HLL114 (Hill Center, Busch) [Contact: Computer Science, cs@rutgers.edu]

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
}
