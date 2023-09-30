/**
 * @Kimberly Donnarumma
 * @Daniel Zhang
 */

import java.util.Arrays;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

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
    private void grow() {
        int newSize = events.length + GROW_BY;
        Event[] newArray = new Event[newSize];
        for (int i = 0; i < events.length; i++) {
            newArray[i] = events[i];
        }
        events = newArray;
    } //increase the capacity by 4

    public boolean add(Event newEvent) {
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
    public boolean contains(Event event) {
        if(find(event) == NOT_FOUND){
            return false;
        }
        return true;
    }
    public boolean calendarIsEmpty(){
        if(numEvents == 0){
            System.out.println("Event calendar is empty!");
            return true;
        }
        return false;
    }
    public void print() {
        if(calendarIsEmpty()){
            return;
        }
        System.out.println("* Event calendar *");
        printDefault();
    } //print the array as is

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
     * Sorts and prints the array by campus.
     */
    public void printByCampus() {
        if(calendarIsEmpty()){
            return;
        }
        System.out.println("* Event calendar by campus and building *");
        for(int i = 1; i < numEvents; i++){
            Event currentEvent = events[i];
            int previousEvent = i - 1;

            while(previousEvent >= 0 &&
                    events[previousEvent].getLocation().getCampus().compareTo(currentEvent.getLocation().getCampus()) > 0){
                events[previousEvent + 1] = events[previousEvent];
                previousEvent -= 1;
            }
            events[previousEvent + 1] = currentEvent;
        }
        organizeBuilding();
        printDefault();
    }

    /**
     * Sorts the array by building.
     */
    private void organizeBuilding() {
        if(calendarIsEmpty()){
            return;
        }
        for(int i = 1; i < numEvents; i++){
            Event currentEvent = events[i];
            int previousEvent = i - 1;

            while(previousEvent >= 0 &&
                    events[previousEvent].getLocation().getBuildingName().compareTo(currentEvent.getLocation().getBuildingName()) > 0){
                events[previousEvent + 1] = events[previousEvent];
                previousEvent -= 1;
            }
            events[previousEvent + 1] = currentEvent;
        }
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
                    events[previousEvent].getContact().getDepartment().compareTo(currentEvent.getContact().getDepartment()) > 0){
                events[previousEvent + 1] = events[previousEvent];
                previousEvent -= 1;
            }
            events[previousEvent + 1] = currentEvent;
        }
        printDefault();
    }
}
