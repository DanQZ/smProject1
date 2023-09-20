/**
 * @Kimberly Donnarumma
 * @
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
    public void run(){
        System.out.println("Event Organizer Running...");
        Scanner scanner = new Scanner(System.in);
        String newInput = "";
        boolean runningOrganizer = true;
        while(runningOrganizer){
            newInput = scanner.nextLine();
            parseAndRunInput(newInput);
        }
        scanner.close();
    }

    void parseAndRunInput(String newInput){
        System.out.println("input: " + newInput);
        String[] tokens = tokenize(newInput, " ");
        int i = 0;
        for (String checkedToken:
                tokens) {
            System.out.println("Token " + i + ": " + checkedToken);
            i++;
        }
        String commandToken = tokens[0];
        String dateToken = tokens[1];
        String timeslotToken = tokens[2];
        String locationToken = tokens[3];
        String departmentToken = tokens[4];
        String emailToken = tokens[5];
        String durationMinutesToken = tokens[6];
        if(!commandIsValid(commandToken)){
            System.out.println(commandToken + " is an invalid command!");
            return;
        }

        Date newDate = parseAndCreateDate(dateToken);
        Timeslot newTimeslot = parseAndCreateTimeslot(timeslotToken);
        Location newLocation = parseAndCreateLocation(locationToken);
        Department newDepartment = parseAndCreateDepartment(departmentToken);
        String newEmail = emailToken;
        int newDuration = -1;
        if(durationMinutesToken != null){
            newDuration = Integer.parseInt(durationMinutesToken);
        }
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

    private boolean commandIsValid(String command){
        switch (command){
            default:
                return false;
            case "A":
                break;
            case "R":
                break;
            case "P":
                break;
            case "PE":
                break;
            case "PC":
                break;
            case "PD":
                break;
            case "Q":
                break;
        }
        return true;
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
        System.out.println("New date year: " + year + ", month: " + month + ", day: " + day);

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
                System.out.println("invalid timeslot");
                return null;
            case "morning":
                break;
            case "afternoon":
                newSlot = Timeslot.AFTERNOON;
                break;
            case "eveninng":
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
        Location newLocation = Location.AB2225;
        switch (locationArg){
            default:
                System.out.println("invalid location!");
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
            System.out.println("null departmentArg");
            return null;
        }
        Department newDepartment = Department.CS;
        switch (departmentArg) {
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

    private void addEvent(){

    }
}
