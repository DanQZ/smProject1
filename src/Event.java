/**
 * Event object class used to construct an event object.
 * @ Kimberly Donnarumma
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

    public Date getDate(){
        return date;
    }

    @Override
    public int compareTo(Event event){
        if(this.date.compareTo(event.date) > 0){
            return 1;
        }
        if(this.date.compareTo(event.date) < 0){
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Event){
            Event event = (Event) obj;
            if(this.date.equals(event.date) &&
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
                + date.toString()
                + "][Start: "
                + startTime.getHour()
                + ":"
                + startTime.getMinuteFirstDigit()
                + startTime.getMinuteSecondDigit()
                + "]["
                + getEndTime()
                + "] "
                + location.getRoomNumber()
                + " ("
                + location.getBuildingName()
                + location.getCampus()
                + ") [Contact: "
                + contact.getDepartment().getFullName()
                + ","
                + contact.toString()
                + "]";

        return eventOutput;
    }
    //[Event Date: 10/21/2023] [Start: 2:00pm] [End: 3:00pm] @HLL114 (Hill Center, Busch) [Contact: Computer Science, cs@rutgers.edu]

    /**
     * Private helper method to get the end time of the event.
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

        endtime += endHour + ":" + endMinuteFirstDigit + endMinuteSecondDigit;

        return endtime;
    }
}
