/**
 * @Kimberly Donnarumma
 * @
 */
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
            System.out.println("input: " + newInput);
            String[] tokens = tokenize(newInput);
            int i = 0;
            for (String checkedToken:
                 tokens) {
                System.out.println("Token " + i + ": " + checkedToken);
                i++;
            }
            String command = tokens[0];
            String date = tokens[1];
            String timeOfDay = tokens[2];
            String room = tokens[3];
            String department = tokens[4];
            String email = tokens[5];
            String durationMinutes = tokens[6];
            if(!commandIsValid(tokens[0])){
                System.out.println(command + " is an invalid command!");
                continue;
            }
        }
        scanner.close();
    }

    String[] tokenize(String inputString){
        String[] output = new String[10];
        int curTokenIndex = 0;
        String curToken = "";
        for(int i = 0; i < inputString.length(); i++){
            String curChar = inputString.substring(i, i+1);
            if(inputString.substring(i, i+1).equals(" ")){
                if(!curToken.equals("")){
                    output[curTokenIndex] = curToken;
                    curTokenIndex++;
                }
                curToken = "";
            }
            else{
                curToken += curChar;
            }
        }

        if(!curToken.equals("")){
            output[curTokenIndex] = curToken;
        }

        return output;
    }

    private boolean commandIsValid(String command){
        switch (command){
            default:
                return false;
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

    private void addEvent(){

    }
}
