import java.sql.Array;
import java.util.List;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Pero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String introMsg = "Hello, I'm Pero! What you want?";
        System.out.println(introMsg);

        List<String> storedList = new ArrayList<>();

        //Loop
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {

            System.out.print("What? ");
            input = sc.nextLine();

            if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < storedList.size(); i++) {
                    System.out.println((i + 1) + ". " + storedList.get(i));
                }
            } else {
                System.out.println("added: " + input);
                storedList.add(input);
            }
        }

        String exitMsg = "Ok Bye.";
        System.out.println(exitMsg);
    }
}
