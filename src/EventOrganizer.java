/**
 * @Kimberly Donnarumma
 * @
 */
import java.util.Scanner;
public class EventOrganizer {
    public void run(){
        System.out.println("Event Organizer Running...");
        Scanner scanner = new Scanner(System.in);

        String newInput = "";
        while(!newInput.equals("Q")){
            newInput = scanner.nextLine();
            System.out.println("input: " + newInput);
        }

        scanner.close();
    }

    private void addEvent(){

    }
}
