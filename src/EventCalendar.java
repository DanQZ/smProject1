import java.util.Arrays;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    private final int NOT_FOUND = -1;
    private int find(Event event) {
        int i = 0;
        for (Event checkedEvent: events) {
            if(event.equals(checkedEvent)){
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    } //search an event in the list

    private final int GROW_BY = 4;
    private void grow() {
        int newSize = events.length + GROW_BY;
        Event[] newArray = new Event[newSize];
        for (int i = 0; i < events.length; i++) {
            newArray[i] = events[i];
        }
        events = newArray;
    } //increase the capacity by 4

    public boolean add(Event event) {
        int i = 0;
        while(events[i] != null){
            if(i >= events.length){
                return false;
            }
            i++;
        }
        events[i] = event;
        return true;
    }
    public boolean remove(Event event) {
        int removeIndex = find(event);
        if(removeIndex == NOT_FOUND){
            return false;
        }

        if(removeIndex == events.length-1){
            events[removeIndex] = null;
            return true;
        }

        for(int i = removeIndex; i < events.length; i++){
            events[i] = events[i+1];
        }

        return true;
    }
    public boolean contains(Event event) {
        if(find(event) == NOT_FOUND){
            return false;
        }
        return true;
    }
    public void print() {
        for (Event checkedEvent: events) {
            checkedEvent.toString();
        }
    } //print the array as is

    /**
     * Sorts and prints the array by date. **STILL NEEDS SORT BY TIMESLOT
     */
    public void printByDate() {
        //Sort the array by date; Still needs to be sorted by timeslot
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
        //Print the array
        print();
    } //ordered by date and timeslot

    /**
     * Sorts and prints the array by campus. **STILL NEEDS BUILDINGS
     */
    public void printByCampus() {
        for(int i = 1; i < numEvents; i++){
            Event currentEvent = events[i];
            int previousEvent = i - 1;

            while(previousEvent >= 0 &&
                    events[previousEvent].getLocation().getCampus().compareTo(currentEvent.getLocation().getCampus()) > 0){
                if(events[previousEvent].getStartTime().getHour() > currentEvent.getStartTime().getHour()) {
                    //Insert code to organize by timeslot.
                }
                else{
                    events[previousEvent + 1] = events[previousEvent];
                    previousEvent -= 1;
                }
            }
            events[previousEvent + 1] = currentEvent;
        }
        print();
    } //ordered by campus and building/room

    /**
     * Sorts and prints the array by department.
     */
    public void printByDepartment(){
        for(int i = 1; i < numEvents; i++){
            Event currentEvent = events[i];
            int previousEvent = i - 1;

            while(previousEvent >= 0 &&
                    events[previousEvent].getContact().getDepartment().compareTo(currentEvent.getContact().getDepartment()) > 0){
                events[previousEvent + 1] = events[previousEvent];
                previousEvent -= 1;
            }
            events[previousEvent + 1] = currentEvent;
        }
        print();
    } //ordered by department
}
