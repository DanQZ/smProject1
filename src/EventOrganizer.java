/**
 * @Kimberly Donnarumma
 * @
 */
import java.util.Scanner;
public class EventOrganizer {
    public void run(){
        System.out.println("Event Organizer Running...");
        Scanner scanner = new Scanner(System.in);

        System.out.println("asfdasdfasd");
        String newInput = "";
        while(!newInput.equals("q")){
            newInput = scanner.nextLine();
            System.out.println("input: " + newInput);
        }

        scanner.close();
    }
}
