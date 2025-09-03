package pero;

import pero.model.Task;
import pero.model.TaskList;

/** Handles user-facing interactions.*/
public class Ui {
    /** Displays empty line/ buffer for visual spacing. */
    public void showEmptyLine() {
        System.out.println();
    }

    /**
     * Displays an error message to the console.
     *
     * @param message Error message.
     */
    public void showExceptions(String message){
        System.out.println(message);
    }

    /** Displays welcome message.*/
    public void showWelcome() {
        System.out.println("Hello, I'm Pero! I am here to track ur tasks. " +
                "If anything unsure, input 'help' into command line");
    }

    /** Displays exit message. */
    public void showExit() {
        System.out.println("Thankyou for using Pero, and ATB!! Exiting now...");
    }

    /**
     * Displays stored tasks in list if there is.
     * Else prompts users to start.
     *
     * @param tasks List of tasks.
     */
    public void showTaskList(TaskList tasks) {
        if (!tasks.getAllTasks().isEmpty()) {
            System.out.println("Here are the stored tasks in your list:");
            for (int i = 0; i < tasks.getAllTasks().size(); i++) {
                System.out.println((i + 1) + ". " + tasks.getAllTasks().get(i));
            }
        } else {
            System.out.println("No stored tasks in your list yet. Start adding!");
        }
        System.out.println();
    }

    /**
     * Starts users off with guide: possible inputs and explanations.
     */
    public void showGuideLines(){
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
    public void showPrompt(){
        System.out.print("What do you want to do?\n");
    }

    /**
     * Displays confirmation that task has been marked done.
     *
     * @param task Current task.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Ok marked done: " + task);
    }

    /**
     * Displays confirmation that task has been marked undone.
     *
     * @param task Current task.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("Ok marked undone: " + task);
    }

    /**
     * Displays current number of tasks in task list.
     *
     * @param tasks List of tasks.
     */
    public void showTasksSize(TaskList tasks) {
        System.out.println("Now, you have " + tasks.getAllTasks().size() + " tasks in your list.");
    }

    /**
     * Displays confirmation that task is being deleted from the list.
     *
     * @param deletedTask Task that is being deleted.
     */
    public void showDelete(Task deletedTask) {
        System.out.print("Noted, now removing task: ");
        System.out.println(deletedTask.getDescription());
    }

    /**
     * Displays confirmation that a new task has been added.
     *
     * @param currTask Newly added task.
     */
    public void showAddedTask(Task currTask){
        System.out.println("Got it. I've added task:");
        System.out.println(currTask);
    }

    /**
     * Displays confirmation that tasks are being saved to storage.
     *
     * @param tasks List of tasks being saved/
     * @param filePath File path where tasks will be stored at.
     */
    public void showSavingToStorage(TaskList tasks, String filePath){
        System.out.printf("Saving %d tasks into %s%n", tasks.getAllTasks().size(), filePath);
    }


    /**
     * Displays warning that current task line being read from storage has wrong format.
     *
     * @param firstLetter First letter of line which indicates what task type.
     * @param currTaskLine Full line of text read from storage.
     */
    public void showWrongFormat(String firstLetter, String currTaskLine){
        String taskType = switch (firstLetter) {
            case "T" -> "ToDo";
            case "D" -> "Deadline";
            case "E" -> "Event";
            default -> "Unknown Task";
        };
        System.out.println("Skipped invalid " + taskType + " line: " + currTaskLine);
    }


    /**
     * Displays list of tasks that match the keyword user is finding.
     *
     * @param tasks List of tasks accumulated.
     * @param keyword Keyword to search for relevant tasks that have it.
     */
    public void showMatchedTasks(TaskList tasks, String keyword) {
        if (!tasks.getAllTasks().isEmpty()) {
            System.out.printf("Here are the tasks that match '%s' in your list: %n", keyword);
            for (int i = 0; i < tasks.getAllTasks().size(); i++) {
                System.out.println((i + 1) + ". " + tasks.getAllTasks().get(i));
            }
        } else if (keyword.trim().isEmpty()){
            System.out.println("Sorry, please key in the keyword you are looking to find.");

        } else {
            System.out.printf("Sorry, no tasks match '%s' in your list. %n", keyword);
        }
    }
}
