package pero;

import java.io.IOException;
import java.util.Scanner;

public class Pero {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;


    /**
     * Constructor to initialise Pero: UI, Storage, TaskList.
     * If invalid filepath, just load empty tasklist
     *
     * @param filePath file path to storage.
     */
    public Pero(String filePath) {

        this.ui = new Ui();
        this.storage = new Storage(filePath);

        // Temp task list so that it compiles w/o error:
        // "Variable 'tasks' might already have been assigned to"
        TaskList tasksTemp;

        try {
            tasksTemp = new TaskList(filePath);
        } catch (IOException e) { //
            this.ui.showExceptions(e.getMessage());
            tasksTemp = new TaskList();
        }
        this.tasks = tasksTemp;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        //opening messages
        ui.showWelcome();
        ui.showTaskList(tasks);

        String input = ""; //initialise input
        while (true) {
            ui.showPrompt();
            input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;  // exit the loop immediately
            }

            try {
                if (input.equalsIgnoreCase("help")) {
                    ui.showGuideLines();
                } else if (input.equalsIgnoreCase("list")) {
                    ui.showTaskList(tasks);

                } else if (input.matches("mark (\\d+)")) {
                    // get index by converting string to int
                    // split input and get second part, -1 to match 0-indexing
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task currTask = tasks.markTask(index);
                    ui.showMarkedTask(currTask);

                } else if (input.matches("unmark (\\d+)")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task currTask = tasks.unmarkTask(index);
                    ui.showUnmarkedTask(currTask);

                } else if (input.matches("delete (\\d+)")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task deletedTask = tasks.removeTask(index);
                    ui.showDelete(deletedTask);
                    ui.showTasksSize(tasks);

                } else if (input.startsWith("todo".toLowerCase())
                        || input.startsWith("deadline".toLowerCase())
                        || input.startsWith("event".toLowerCase())) {
                    // if input starts with correct task type
                    // if wrong, throws PeroException
                    Task currTask = tasks.addTaskFromInput(input);
                    ui.showAddedTask(currTask);
                    ui.showTasksSize(tasks);

                } else {
                    throw new PeroException("Oops! Idk whats that, pls try again.");
                }
            } catch (PeroException e) { //catches all the exception from current user input
                ui.showExceptions(e.getMessage());
            }
            ui.showEmptyLine();
        }

        try {
            ui.showSavingToStorage(tasks, storage.getFilePath());
            storage.saveList(tasks);
        } catch (IOException e) {
            ui.showExceptions(e.getMessage());
        }
        ui.showExit();
    }


    public static void main(String[] args) {
        new Pero("Pero_storage.txt").run();
    }
}
