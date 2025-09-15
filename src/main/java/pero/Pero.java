package pero;

import pero.command.Command;
import pero.exception.PeroException;
import pero.model.TaskList;
import pero.ui.GuiUi;
import pero.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Core application logic, independent of CLI or GUI
 */
public class Pero {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final GuiUi guiUi;


    /**
     * Constructor to initialise Pero: UI, Storage, TaskList.
     * If invalid filepath, just load empty tasklist.
     *
     * @param filePath file path to storage.
     */
    public Pero(String filePath) {

        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.guiUi = new GuiUi();

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

    /**
     * Continuous loop waiting for scanner results, parsing and output using ui for each input
     */
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
                    case FIND -> {
                        String keyword = cmd.taskInput;
                        TaskList matchingResults = tasks.findTasks(keyword);
                        ui.showMatchedTasks(matchingResults,keyword);
                    }
                    case INVALID -> {
                        throw new PeroException("Incorrect input. If you need some help, input 'help'.");
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

    /**
     * Mirrors run(), but now is for GUI
     * returns String i.e. label for each user input
     */
    public String getResponse(String input) {
        Command cmd = Parser.parseInputCommand(input);
        switch (cmd.type) {
        case BYE -> {
            return guiUi.getExitMessage(); //break out of current loop
        }
        case HELP -> {
            return guiUi.getGuidelines();
        }
        case LIST -> {
            return guiUi.getTaskListMessage(tasks);
        }
        case MARK -> {
            return guiUi.getMarkedTaskMessage(tasks.markTask(cmd.index));
        }
        case UNMARK -> {
            return guiUi.getUnmarkedTaskMessage(tasks.unmarkTask(cmd.index));
        }
        case DELETE -> {
            return guiUi.getDeleteTaskMessage(tasks.removeTask(cmd.index))
                    + "\n"
                    + guiUi.getTasksSizeMessage(tasks);
        }
        case TODO, DEADLINE, EVENT -> {
            return guiUi.getAddedTaskMessage(tasks.removeTask(cmd.index))
                    + "\n"
                    + guiUi.getTasksSizeMessage(tasks);
        }
        case FIND -> {
            String keyword = cmd.taskInput;
            TaskList matchingResults = tasks.findTasks(keyword);
            return guiUi.getMatchedTasks(matchingResults, keyword);
        }
        case INVALID -> {
            return "Incorrect input. If you need some help, input 'help'.";
        }
        }
        return "Idk man.";
    }
}