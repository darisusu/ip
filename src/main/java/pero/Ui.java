package pero;

import java.util.List;

/** Handles user-facing interactions.*/
public class Ui {
    /** Displays empty line/ buffer for visual spacing. */
    public static void showEmptyLine() {
        System.out.println();
    }

    /**
     * Displays an error message to the console.
     * @param message Error message.
     */
    public static void showExceptions(String message){
        System.out.println(message);
    }

    /** Displays welcome message.*/
    public static void showWelcome() {
        System.out.println("Hello, I'm Pero! I am here to track ur tasks.");
    }

    /** Displays exit message. */
    public static void showExit() {
        System.out.println("Thankyou for using Pero, and ATB!! Exiting now...");
    }

    /**
     * Displays stored tasks in list if there is.
     * Else prompts users to start.
     *
     * @param tasks the list of tasks.
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

    /** Displays prompt for task.*/
    public static void showPrompt(){
        System.out.print("What do you want to do?\n");
    }

    /**
     * Displays confirmation that task has been marked done.
     *
     * @param task the current task.
     */
    public static void showMarkedTask(Task task) {
        System.out.println("Ok marked done: " + task);
    }

    /**
     * Displays confirmation that task has been marked undone.
     *
     * @param task the current task.
     */
    public static void showUnmarkedTask(Task task) {
        System.out.println("Ok marked undone: " + task);
    }

    /**
     * Displays current number of tasks in task list.
     *
     * @param tasks the list of tasks.
     */
    public static void showTasksSize(List<Task> tasks) {
        System.out.println("Now, you have " + tasks.size() + " tasks in your list.");
    }

    /**
     * Displays confirmation that task is being deleted from the list.
     *
     * @param tasks the list of tasks.
     * @param index the index of task being removed.
     */
    public static void showDelete(List<Task> tasks, int index) {
        System.out.print("Noted, now removing task: ");
        System.out.println(tasks.get(index).description);
    }

    /**
     * Displays confirmation that a new task has been added.
     *
     * @param currTask the newly added task.
     */
    public static void showAddedTask(Task currTask){
        System.out.println("Got it. I've added task:");
        System.out.println(currTask);
    }

    /**
     * Displays confirmation that tasks are being saved to storage.
     *
     * @param tasks the list of tasks being saved/
     * @param filePath the file path where tasks will be stored at.
     */
    public static void showSavingToStorage(List<Task> tasks, String filePath){
        System.out.printf("Saving %d tasks into %s%n", tasks.size(), filePath);
    }


    /**
     * Display warning that current task line being read from storage has wrong format.
     *
     * @param firstLetter the first letter of line which indicates what task type.
     * @param currTaskLine the full line of text read from storage.
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
