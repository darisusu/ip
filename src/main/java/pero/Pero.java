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
        boolean isRunning = true;
        while (isRunning) {
            ui.showPrompt();
            input = sc.nextLine();

            //find out what command type the input line is
            Command cmd = Parser.parseInputCommand(input);
            try {
                switch (cmd.type) {
                    case BYE -> isRunning = false; //break out of current loop
                    case HELP -> ui.showGuideLines();
                    case LIST -> ui.showTaskList(tasks);
                    case MARK -> ui.showMarkedTask(tasks.markTask(cmd.index));
                    case UNMARK -> ui.showUnmarkedTask(tasks.unmarkTask(cmd.index));
                    case DELETE -> {
                        ui.showDelete(tasks.removeTask(cmd.index));
                        ui.showTasksSize(tasks);
                    }
                    case TODO, DEADLINE, EVENT -> {
                        ui.showAddedTask(tasks.addTaskFromInput(input));
                        ui.showTasksSize(tasks);
                    }
                }

            // unknown task type identified, print error
            // go to next iteration: await next user input to scan
            } catch (PeroException e) {
                ui.showExceptions(e.getMessage());
            }
            ui.showEmptyLine();
        }

        //after ending loop of scanning when "bye" user input
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
