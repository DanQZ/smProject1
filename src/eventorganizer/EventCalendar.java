package eventorganizer;

/**
 * Define the abstract data type EventCalendar.
 * @KimberlyDonnarumma
 * @DanielZhang
 */

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    /**
     Creates an eventCalendar with 4 empty events
     */
    public EventCalendar(){
        events = new Event[4];
        numEvents = 0;
    }

    private final int NOT_FOUND = -1;
    private int find(Event event) {
        int i = 0;
        for (Event checkedEvent: events) {
            if(event.equals(checkedEvent)){
                // System.out.println("Event match found at index " + i);
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    } //search an event in the list

    private final int GROW_BY = 4;
    /**
     Increases the size of the calendar array by GROW_BY, keeps existing events in it
     */
    private void grow() {
        int newSize = events.length + GROW_BY;
        Event[] newArray = new Event[newSize];
        for (int i = 0; i < events.length; i++) {
            newArray[i] = events[i];
        }
        events = newArray;
    } //increase the capacity by 4

    /**
     Adds a new event to the calendar and increases numEvent by 1
     Calls grow() if necessary
     Does not overwrite existing events at same attributes
     @param newEvent takes in an Event to be added to the calendar
     @return if adding was successful
     */
    public boolean newCalendarEvent(Event newEvent) {
        if(numEvents == events.length){
            grow();
        }
        if(find(newEvent) != NOT_FOUND){
            // event already booked at location and time
            return false;
        }
        int i = 0;
        while(events[i] != null){
            if(i >= events.length){
                return false;
            }
            i++;
        }
        events[i] = newEvent;
        numEvents++;
        return true;
    }

    /**
     Removes events from calendar a certain location, time, and date, and lowers numEvents by 1
     Does nothing if the event does not exist
     @param event's location, timeslot, and date are used to compare to existing events
     @return if an event was removed
     */
    public boolean remove(Event event) {
        int removeIndex = find(event);
        if(removeIndex == NOT_FOUND){
            return false;
        }

        if(removeIndex == events.length-1){
            events[removeIndex] = null;
            numEvents--;
            return true;
        }

        for(int i = removeIndex; i < events.length - 1; i++){
            events[i] = events[i+1];
        }
        events[events.length-1] = null;

        numEvents--;
        return true;
    }


    /**
     Checks for existence of an event
     @param event is used to get location, time, and date
     @return true if it exists, false otherwise
     */
    public boolean calendarContains(Event event) {
        if(find(event) == NOT_FOUND){
            return false;
        }
        return true;
    }
    /**
     Checks for existence of any events
     @return true if calendar has no events, false otherwise
     */
    public boolean calendarIsEmpty(){
        if(numEvents == 0){
            System.out.println("Event calendar is empty!");
            return true;
        }
        return false;
    }
    /**
     Prints all events in the calendar in the current order in array without sorting
     */
    public void print() {
        if(calendarIsEmpty()){
            return;
        }
        System.out.println("* Event calendar *");
        printDefault();
    } //print the array as is

    /**
     Prints all events in the calendar in the current order in array with no header
     */
    private void printDefault(){
        int i = 0;
        for (Event checkedEvent: events) {
            if(checkedEvent != null){
                System.out.println(checkedEvent.toString());
            }
            i++;
        }
        System.out.println("* end of event calendar *");
    }

    /**
     * Sorts and prints the array by date.
     */
    public void printByDate() {
        //Sort the array by date; Still needs to be sorted by timeslot
        if(calendarIsEmpty()){
            return;
        }
        System.out.println("* Event calendar by event date and start time *");
        for(int i = 1; i < numEvents; i++){
            Event currentEvent = events[i];
            int previousEvent = i - 1;

            while(previousEvent >= 0 &&
                events[previousEvent].getDate().compareTo(currentEvent.getDate()) > 0){
                events[previousEvent + 1] = events[previousEvent];
                previousEvent -= 1;
            }
            events[previousEvent + 1] = currentEvent;
        }
        organizeTimeslot();
        printDefault();
    }

    /**
     * Sorts the array by timeslot.
     */
    private void organizeTimeslot() {
        for(int i = 1; i < numEvents; i++){
            Event currentEvent = events[i];
            int previousEvent = i - 1;
                while (previousEvent >= 0 &&
                        events[previousEvent].getStartTime().getHour() >
                                currentEvent.getStartTime().getHour() &&
                        currentEvent.getDate().equals(events[previousEvent].getDate())) {
                    events[previousEvent + 1] = events[previousEvent];
                    previousEvent -= 1;
                }
            events[previousEvent + 1] = currentEvent;
        }
    }

    /**
     * Sorts and prints the array by campus, and if same campus then sorts by whichever has a smaller alphabetical building.
     */
    public void printByCampus() {
        if(calendarIsEmpty()){
            return;
        }
        System.out.println("* Event calendar by campus and building *");

        for(int i = 0; i < numEvents; i++) {
            Event smallestEvent = events[i]; // sets default smallestEvent
            int smallestIndex = i;
            for (int k = i; k < numEvents; k++) {
                String campus1 = smallestEvent.getLocation().getCampus();
                String campus2 = events[k].getLocation().getCampus();
                if (campus2.compareTo(campus1) < 0) { // checked event has smaller alphabetical campus
                    smallestEvent = events[k];
                    smallestIndex = k;
                }
                if(campus2.compareTo(campus1) == 0){ // same campus, check buildings alphabetically
                    String building1 = smallestEvent.getLocation().getBuildingName();
                    String building2 = events[k].getLocation().getBuildingName();
                    if(building2.compareTo(building1) < 0){
                        smallestEvent = events[k];
                        smallestIndex = k;
                    }
                }
            }
            Event swappedEvent = events[i];
            events[i] = smallestEvent;
            events[smallestIndex] = swappedEvent;
        }
        printDefault();
    }

    /**
     * Sorts and prints the array by department.
     */
    public void printByDepartment(){
        if(calendarIsEmpty()){
            return;
        }
        System.out.println("* Event calendar by department *");
        for(int i = 1; i < numEvents; i++){
            Event currentEvent = events[i];
            int previousEvent = i - 1;
            while(previousEvent >= 0 &&
                    events[previousEvent].getContact().getDepartment().toString().compareTo(currentEvent.getContact().getDepartment().toString()) > 0){
                events[previousEvent + 1] = events[previousEvent];
                previousEvent -= 1;
            }
            events[previousEvent + 1] = currentEvent;
        }
        printDefault();
    }
}
