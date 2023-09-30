/**
 * @Kimberly Donnarumma
 * @Daniel Zhang
 */

import java.sql.Time;
import java.util.Scanner;
public class EventOrganizer {
    EventCalendar curCalendar;

    /**
     Runs a while loop until the user inputs Q as the command to quit
     */
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

    /**
     Parses input into multiple tokens separated by spaces, and then attempts to run the command and arguments
     @param newInput takes in the whole command string
     @return false if Q command given, indicating to quit running this program
     */
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
     Runs command if token is valid
     @param tokens takes an array of each string token.
     @return true if the command was run otherwise returns false.
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

    /**
     Creates an event with identical time and location for find-and-delete purpose, where all other attributes are defaulted to an arbitrary valid attribute
     @param tokens takes an array of each string token.
     @return the created event
     */
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


    /**
     Adds an event to the current calendar with the command tokens, and prints out the result
     @param tokens takes an array of each string token.
     */
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


    /**
     Removes an event at the location and time if it exists, and prints out the result
     @param tokens takes an array of each string token.
     */
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
    /**
     Helper method to check if an event is valid before adding it to the calendar
     @param newEvent takes the newly created event object
     @return true if valid, false if invalid
     */
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
        if(!newEvent.getContact().isValid()){
            System.out.println("Invalid contact information!");
            return false;
        }
        return true;
    }

    /**
     Helper method to check if the command (token[0]) is valid
     @param command takes in the first token in the entire command
     @return true if valid, false if invalid
     */
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

    /**
     Splits up a string into tokens, splitting based on the separator character, and returns those tokens
     @param inputString takes in the entire command string
     @param separator takes in what separates the tokens in the command
     @return a string array of each token in their order in inputString
     */
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

    /**
     Creates and returns a new Date
     @param dateArg takes in the date token
     @return null if invalid date, otherwise returns newly created Date
     */
    private Date parseAndCreateDate(String dateArg){
        if(dateArg == null){
            System.out.println("dateArg is null");
            return null;
        }
        String[] dateTokens = tokenize(dateArg, "/");
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

    /**
     Creates and returns a new Timeslot
     @param timeslotArg takes in the timeslot token
     @return null if invalid date, otherwise returns newly created Timeslot
     */
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

    /**
     Creates and returns a new Location
     @param locationArg takes in the location token
     @return null if invalid date, otherwise returns newly created Location
     */
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

    /**
     Creates and returns a new Department
     @param departmentArg takes in the department token
     @return null if invalid date, otherwise returns newly created Department
     */
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
