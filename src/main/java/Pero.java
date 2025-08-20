import java.sql.Array;
import java.util.List;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Pero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String introMsg = "Hello, I'm Pero! I am here to track ur tasks.";
        System.out.println(introMsg);

        List<Task> storedList = new ArrayList<>();

        //Loop
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {

            System.out.print("What's the task? ");
            input = sc.nextLine();

            if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < storedList.size(); i++) {
                    System.out.println((i + 1) + ". " + storedList.get(i));
                }
            } else if (input.matches("mark (\\d+)")) {
                // get index by converting string to int
                // split input and get second part, -1 to match 0-indexing
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                storedList.get(index).markAsDone();
                System.out.println("Ok marked done: \n" + storedList.get(index));

            } else if (input.matches("unmark (\\d+)")){
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                storedList.get(index).markAsUndone();
                System.out.println("Ok marked undone: \n" + storedList.get(index));

            } else {
                System.out.println("Added task: " + input);
                Task t = new Task(input);
                storedList.add(t);
            }
        }

        String exitMsg = "Ok GoodLuck.";
        System.out.println(exitMsg);
    }



}
