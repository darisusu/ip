package pero;

import java.util.List;

public class Ui {

    /** Prints empty line/ buffer
     *
     */
    public static void showEmptyLine() {
        System.out.println();
    }

    /**
     *
     * @param message Error message
     */
    public static void showExceptions(String message){
        System.out.println(message);
    }


    /**
     * Prints welcome message.
     */
    public static void showWelcome() {
        System.out.println("Hello, I'm Pero! I am here to track ur tasks.");
    }

    /**
     * Prints exit message.
     */
    public static void showExit() {
        System.out.println("Thankyou for using Pero, and ATB!! Exiting now...");
    }

    /**
     * Prints stored tasks in list if there is.
     * Else prompts users to start.
     *
     * @param tasks list of tasks.
     */
    public static void showTaskList(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println("Here are the stored tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        } else {
            System.out.println("No stored tasks in your list yet. Start adding!");
        }
        System.out.println();
    }

    /**
     * Starts users off with guide: possible inputs and explanations.
     */
    public static void showGuideLines(){
        System.out.println("Welcome to Pero! Here’s how to use me:");
        System.out.println();
        System.out.println("Adding tasks:");
        System.out.println("- todo [description]                                    : Add a simple task");
        System.out.println("- deadline [description] /by [YYYY-DD-MM HHmm]          : Add a task with a deadline");
        System.out.println("- event [description] /from [YYYY-DD-MM HHmm] /to [YYYY-DD-MM HHmm] : Add an event with start and end times");
        System.out.println();
        System.out.println("Managing tasks:");
        System.out.println("- list                                                  : Show all tasks");
        System.out.println("- mark [task number]                                    : Mark a task as done");
        System.out.println("- unmark [task number]                                  : Mark a task as not done");
        System.out.println("- delete [task number]                                  : Delete a task");
        System.out.println();
        System.out.println("Exiting:");
        System.out.println("- bye                                                   : Exit Pero and saves tasks");
        System.out.println();
        System.out.println("Note: Date/time format must be YYYY-MM-dd HH:mm (e.g. 2025-09-01 16:00)");
        System.out.printf("Now lets start!%n");

    }

    /**
     * Prints prompt for task.
     */
    public static void showPrompt(){
        System.out.print("What do you want to do?\n");
    }

    /**
     * Prints "Ok marked done: [task]".
     *
     * @param task current task.
     */
    public static void showMarkedTask(Task task) {
        System.out.println("Ok marked done: " + task);
    }

    /**
     * Prints "Ok marked undone: [task]".
     *
     * @param task current task.
     */
    public static void showUnmarkedTask(Task task) {
        System.out.println("Ok marked undone: " + task);
    }

    public static void showTasksSize(List<Task> tasks) {
        System.out.println("Now, you have " + tasks.size() + " tasks in your list.");
    }

    public static void showDelete(List<Task> tasks, int index) {
        System.out.print("Noted, now removing task: ");
        System.out.println(tasks.get(index).description);
    }

    public static void showAddedTask(Task currTask){
        System.out.println("Got it. I've added task:");
        System.out.println(currTask);
    }

    public static void showSavingToStorage(List<Task> tasks, String filePath){
        System.out.printf("Saving %d tasks into %s%n", tasks.size(), filePath);
    }


    /**
     * Display message when current task line being read from storage has wrong format.
     *
     * @param firstLetter first letter of line which indicates what task type
     * @param currTaskLine current task line being read
     */
    public static void showWrongFormat(String firstLetter, String currTaskLine){
        String taskType = switch (firstLetter) {
            case "T" -> "ToDo";
            case "D" -> "Deadline";
            case "E" -> "Event";
            default -> "Unknown Task";
        };
        System.out.println("Skipped invalid " + taskType + " line: " + currTaskLine);
    }











    }
