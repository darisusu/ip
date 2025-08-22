import java.util.Scanner;
import java.util.ArrayList;

// can improve polymorphism of each subclass inheriting task
// can include error handling for mark/unmark


public class Pero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String introMsg = "Hello, I'm Pero! I am here to track ur tasks.";
        System.out.println(introMsg);

        ArrayList<Task> tasks = new ArrayList<>();

        //Loop
        String input = "";
        while (true) {
            System.out.print("What's the task?\n");
            input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;  // exit the loop immediately
            }

            try {
                if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (input.matches("mark (\\d+)")) {
                    // get index by converting string to int
                    // split input and get second part, -1 to match 0-indexing
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(index).markAsDone();
                    System.out.println("Ok marked done:\n" + tasks.get(index));

                } else if (input.matches("unmark (\\d+)")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(index).markAsUndone();
                    System.out.println("Ok marked undone:\n" + tasks.get(index));

                } else if (input.matches("delete (\\d+)")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    System.out.println("Noted, I will remove this task:");
                    System.out.println(tasks.get(index));
                    tasks.remove(index);
                    System.out.println("Now, you have " + tasks.size() + " tasks in the list.");


                } else if (input.startsWith("todo")) {
                    Task t = ToDo.fromInput(input);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (input.startsWith("deadline")) {
                    Task t = Deadline.fromInput(input);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (input.startsWith("event")) {
                    Task t = Event.fromInput(input);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else {
//                    System.out.println("Added task: " + input);
//                    Task t = new Task(input);
//                    tasks[taskCount] = t;
//                    taskCount++;
//                    System.out.println("Added task: " + input);

//                    System.out.println("Try again!");
                    throw new PeroException("Oops! Idk whats that, pls try again.");
                }
            } catch (PeroException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("");
        }
        String exitMsg = "Ok thankyou for using Pero, good luck.";
        System.out.println(exitMsg);
    }
}
