import java.util.Scanner;

public class Pero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String introMsg = "Hello, I'm Pero! What you want?";
        System.out.println(introMsg);

        //Loop
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            System.out.print("What? ");
            input = sc.nextLine();
            System.out.println(input);
        }

        String exitMsg = "Ok Bye.";
        System.out.println(exitMsg);
    }
}
