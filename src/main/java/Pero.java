import java.util.Scanner;

public class Pero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String introMsg = "Hello, I'm Pero! I am here to track ur tasks.";
        System.out.println(introMsg);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        //Loop
        String input = "";
        while (true) {
            System.out.print("What's the task? \n");
            input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;  // exit the loop immediately
            }

            //list out all tasks
            if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (input.matches("mark (\\d+)")) {
                // get index by converting string to int
                // split input and get second part, -1 to match 0-indexing
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("Ok marked done: \n" + tasks[index]);

            } else if (input.matches("unmark (\\d+)")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markAsUndone();
                System.out.println("Ok marked undone: \n" + tasks[index]);

            } else if (input.startsWith("todo")) {
                System.out.println("Got it. I've added this task: ");
                String taskToDo = input.substring(5); //starts at index 5, remove "todo "
                Task t = new ToDo(taskToDo);
                tasks[taskCount] = t;
                System.out.println(t);
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");

            } else if (input.startsWith("deadline")) {
                System.out.println("Got it. I've added this task: ");
                String[] taskDeadline = input.substring(9).split(" /by "); //starts at index 9, remove "deadline "
                String task = taskDeadline[0];
                String by = taskDeadline[1];
                Task t = new Deadline(task, by);
                tasks[taskCount] = t;
                System.out.println(t);
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");

            } else if (input.startsWith("event")) {
                System.out.println("Got it. I've added this task: ");
                String[] taskEvent = input.substring(6).split(" /from "); //starts at index 6, remove "event "
                String task = taskEvent[0];
                String[] time = taskEvent[1].split(" /to ");
                String from = time[0];
                String to = time [1];
                Task t = new Event(task,from,to);
                tasks[taskCount] = t;
                System.out.println(t);
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");

            } else {
//                System.out.println("Added task: " + input);
//                Task t = new Task(input);
//                tasks[taskCount] = t;
//                taskCount++;
//                System.out.println("Added task: " + input);
                System.out.println("Try again!");
            }
            System.out.println("");
        }
        String exitMsg = "Ok thankyou for using Pero, good luck.";
        System.out.println(exitMsg);
    }
}
