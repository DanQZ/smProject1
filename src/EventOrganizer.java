/**
 * @Kimberly Donnarumma
 * @Daniel Zhang
 */

import java.sql.Time;
import java.util.Scanner;
public class EventOrganizer {
    /*
        R command, to cancel an event and remove the specified event from the calendar, for example,
            R 12/22/2023 MORNING HLL114
            You must check if the date is valid. Refer to the sample output for the messages to display.
        • P command, to display the event calendar on the console/terminal, with the current order in the array.
        • PE command, to display the event calendar on the console/terminal, sorted by the event date and timeslot of the
            timeslot. That is, if two events have the same date, display the events in the order of the timeslots.
        • PC command, to display the event calendar on the console/terminal, sorted by campus and building/room. That
            is, if two events are held on the same campus, display the events in the order of building/room number.
        • PD command, to display the event calendar on the console/terminal, sorted by the department in the contact. If
            two events have the same hosting department, the order doesn’t matter.
        • Q command, to stop the execution of the software and display " Event Organizer terminated."
     */

    /*
        A 2/29/2024 afternoon HLL114 CS cs@rutgers.edu 60
        The above command line starts with the A command, followed by a future event date, timeslot, location, contact
        and duration in minutes. The date shall be given in mm/dd/yyyy format, and the date should be within 6 months
        from today’s date. All the campus locations provide three timeslots daily: 10:30am, 2:00pm and 6:30pm. A
        timeslot is entered as either “morning”, “afternoon”, or “evening”. A location is entered in acronym listed in page
        #1, for example, HIL114. A contact of an event includes the hosting department and an email address. A
        department name is entered in acronym listed in page #1, for example, CS. The email address must have the
        domain name @rutgers.edu. The event duration is a positive integer representing the number of minutes. The
        duration is at least 30 minutes and at most 120 minutes.
     */
    EventCalendar curCalendar;
    public void run(){
        System.out.println("Event Organizer Running...");
        Scanner scanner = new Scanner(System.in);
        String newInput = "";
        curCalendar = new EventCalendar();
        while(true){
            newInput = scanner.nextLine();
            boolean continueRunning = parseAndRunInput(newInput);
            if(!continueRunning){
                break;
            }
        }
        System.out.println("Event Organizer terminated.");
        scanner.close();
    }

    boolean parseAndRunInput(String newInput){

       //System.out.println("input: " + newInput);
        String[] tokens = tokenize(newInput, " ");

        if(tokens[0].equals("Q")){
            return false;
        }

        int i = 0;
        for (String checkedToken:
                tokens) {
             //System.out.println("Token " + i + ": [" + checkedToken + "]");
            i++;
        }
        /*
        commandToken = tokens[0];
        dateToken = tokens[1];
        timeslotToken = tokens[2];
        locationToken = tokens[3];
        departmentToken = tokens[4];
        emailToken = tokens[5];
        durationMinutesToken = tokens[6];
        */
        if(!commandIsValid(tokens[0])){
            System.out.println(tokens[0] + " is an invalid command!");
            return true;
        }

        runCommand(tokens);
        int newDuration = -1;
        if(tokens[6] != null){
            newDuration = Integer.parseInt(tokens[6]);
        }

        return true;
    }
    /**
     Remove the given student from the list.
     Does nothing if the student is not in the list.
     @param tokens takes an array of each string token.
     @return true if the command was run otherwise false.
     */
    private boolean runCommand(String[] tokens){
        boolean ran = false;

        switch (tokens[0]){
            default:
                return false;
            case "A": // add
                addEvent(tokens);
                break;
            case "R": // cancel
                removeEvent(tokens);
                break;
            case "P": // display in current order in array
                displayEventsCurrentOrder();
                break;
            case "PE": // display sorted by date/timeslot
                displayEventsSorted("date");
                break;
            case "PC": // display sorted by campus/building/room
                displayEventsSorted("campus");
                break;
            case "PD": // display sorted by department
                displayEventsSorted("department");
                break;
        }
        return ran;
    }

    /**
     Creates an event using existing tokens
     Does nothing if something in the command is invalid
     @param tokens is a string array of each token
     @return the created event.
     */
    private Event createEvent(String[] tokens){
        Date newDate = parseAndCreateDate(tokens[1]);
        Timeslot newTimeslot = parseAndCreateTimeslot(tokens[2]);
        Location newLocation = parseAndCreateLocation(tokens[3]);
        Department newDepartment = parseAndCreateDepartment(tokens[4]);
        String newEmail = tokens[5];
        int newDuration = Integer.parseInt(tokens[6]);

        Contact newContact = new Contact(newDepartment, newEmail);
        Event newEvent = new Event(
                newDate,
                newTimeslot,
                newLocation,
                newContact,
                newDuration
        );
        return newEvent;
    }

    private Event createTempEvent(String[] tokens){
        Date newDate = parseAndCreateDate(tokens[1]);
        Timeslot newTimeslot = parseAndCreateTimeslot(tokens[2]);
        Location newLocation = parseAndCreateLocation(tokens[3]);
        Department newDepartment = Department.CS;
        String newEmail = "cs@rutgers.edu";
        int newDuration = 30;

        Contact newContact = new Contact(newDepartment, newEmail);
        Event tempEvent = new Event(
                newDate,
                newTimeslot,
                newLocation,
                newContact,
                newDuration
        );
        return tempEvent;
    }

    private void addEvent(String[] tokens){

        Event newEvent = createEvent(tokens);

        boolean validEvent = newEventIsValid(newEvent);
        if(!validEvent){
            return;
        }
        boolean success = curCalendar.add(newEvent);
        if(success){
            System.out.println("Event added to the calendar.");
        }
        else{
            System.out.println("The event is already on the calendar.");
            return;
        }
    }

    private void removeEvent(String[] tokens){
        Event tempEvent = createTempEvent(tokens);
        curCalendar.remove(tempEvent);
    }
    private void displayEventsCurrentOrder(){
        curCalendar.print();
    }
    private void displayEventsSorted(String sortBy){
        switch (sortBy){
            default:
                break;
            case "date":
                curCalendar.printByDate();
                break;
            case "campus":
                curCalendar.printByCampus();
                break;
            case "department":
                curCalendar.printByDepartment();
                break;
        }
    }

    private boolean newEventIsValid(Event newEvent){
        if(!newEvent.getDate().isValid()){
            String dateString = newEvent.getDate().toString();
            System.out.println(dateString + ": Invalid calendar date!");
            return false;
        }
        if(newEvent.getDate().pastOrTooFar() == -1){
            String dateString = newEvent.getDate().toString();
            System.out.println(dateString + ": Event date must be a future date!");
            return false;
        }
        if(newEvent.getDate().pastOrTooFar() == 1){
            String dateString = newEvent.getDate().toString();
            System.out.println(dateString + ": Event date must be within 6 months!");
            return false;
        }
        if(newEvent.getStartTime() == null){
            String dateString = newEvent.getDate().toString();
            System.out.println("Invalid time slot!");
            return false;
        }
        if(newEvent.getLocation() == null){
            System.out.println("Invalid location!");
            return false;
        }
        if(curCalendar.contains(newEvent)){
            System.out.println("The event is already on the calendar.");
            return false;
        }
        return true;
    }

    private boolean commandIsValid(String command){
        if(command == null){
            return false;
        }
        switch (command){
            default:
                return false;
            case "A": // add
                break;
            case "R": // cancel
                break;
            case "P": // display in current order in array
                break;
            case "PE": // display sorted by date/timeslot
                break;
            case "PC": // display sorted by campus/building/room
                break;
            case "PD": // display sorted by department
                break;
            case "Q": // quit
                break;
        }
        return true;
    }

    // splits up a string, splitting based on the separator character
    String[] tokenize(String inputString, String separator) {
        String[] output = new String[10];
        int curTokenIndex = 0;
        String curToken = "";
        for (int i = 0; i < inputString.length(); i++) {
            String curChar = inputString.substring(i, i + 1);
            if (inputString.substring(i, i + 1).equals(separator)) {
                if (!curToken.equals("")) {
                    output[curTokenIndex] = curToken;
                    curTokenIndex++;
                }
                curToken = "";
            } else {
                curToken += curChar;
            }
        }

        if (!curToken.equals("")) {
            output[curTokenIndex] = curToken;
        }

        return output;
    }

    // returns null if invalid date, otherwise returns new Date
    private Date parseAndCreateDate(String dateInput){
        if(dateInput == null){
            System.out.println("dateArg is null");
            return null;
        }
        String[] dateTokens = tokenize(dateInput, "/");
        /*
        for(int i = 0; i < 5; i++){
            System.out.println("date token " + i + ": " + dateTokens[i]);
        }
        */

        int month = -61398076;
        int day= -12341234;
        int year= -69;

        try {
            month = Integer.parseInt(dateTokens[0]);
            day = Integer.parseInt(dateTokens[1]);
            year = Integer.parseInt(dateTokens[2]);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid date syntax!");
        }
        Date newDate = new Date(year,month,day);
        // System.out.println("New date year: " + year + ", month: " + month + ", day: " + day);

        return newDate;
    }
    //returns null if invalid
    private Timeslot parseAndCreateTimeslot(String timeslotArg){
        if(timeslotArg == null){
            return null;
        }

        Timeslot newSlot = Timeslot.MORNING;
        String timeslotLower = timeslotArg.toLowerCase();
        switch (timeslotLower){
            default:
                //System.out.println("invalid timeslot");
                return null;
            case "morning":
                break;
            case "afternoon":
                newSlot = Timeslot.AFTERNOON;
                break;
            case "evening":
                newSlot = Timeslot.EVENING;
                break;
        }
        return newSlot;
    }

    private Location parseAndCreateLocation(String locationArg){
        if(locationArg == null){
            System.out.println("null locationArg");
            return null;
        }
        String formattedArg = locationArg.toUpperCase();
        Location newLocation = Location.AB2225;
        switch (formattedArg){
            default:
                return null;
            case "HLL114":
                newLocation = Location.HLL114;
                break;
            case "ARC103":
                newLocation = Location.ARC103;
                break;
            case "BE_AUD":
                newLocation = Location.BE_AUD;
                break;
            case "TIL232":
                newLocation = Location.TIL232;
                break;
            case "AB2225":
                newLocation = Location.AB2225;
                break;
            case "MU302":
                newLocation = Location.MU302;
                break;
        }
        return newLocation;
    }

    private Department parseAndCreateDepartment(String departmentArg) {
        if(departmentArg == null){
            return null;
        }
        String formattedArg = departmentArg.toUpperCase();
        Department newDepartment = Department.CS;
        switch (formattedArg) {
            default:
                System.out.println("invalid department");
                return null;
            case "CS":
                newDepartment = Department.CS;
                break;
            case "EE":
                newDepartment = Department.EE;
                break;
            case "ITI":
                newDepartment = Department.ITI;
                break;
            case "MATH":
                newDepartment = Department.MATH;
                break;
            case "BAIT":
                newDepartment = Department.BAIT;
                break;
        }

        return newDepartment;
    }
}
