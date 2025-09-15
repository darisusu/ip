package pero;

import pero.command.Command;
import pero.exception.PeroException;
import pero.model.TaskList;
import pero.ui.GuiUi;

import java.io.IOException;

/**
 * Core application logic, independent of CLI or GUI
 */
public class Pero {

    private final Storage storage;
    private final TaskList tasks;
    private final GuiUi guiUi;


    /**
     * Constructor to initialise Pero: UI, Storage, TaskList.
     * If invalid filepath, just load empty tasklist.
     *
     * @param filePath file path to storage.
     */
    public Pero(String filePath) {

        this.storage = new Storage(filePath);
        this.guiUi = new GuiUi();

        // Temp task list so that it compiles w/o error:
        // "Variable 'tasks' might already have been assigned to"
        TaskList tasksTemp;

        try {
            tasksTemp = new TaskList(filePath);
        } catch (IOException e) { //
            tasksTemp = new TaskList();
        }
        this.tasks = tasksTemp;
    }

    /**
     * for GUI
     * returns String i.e. label for each user input
     */
    public String getResponse(String input) throws PeroException, IOException {
        Command cmd = Parser.parseInputCommand(input);
        switch (cmd.type) {
        case BYE:
            storage.saveList(tasks);
            return guiUi.getExitMessage(); //break out of current loop

        case HELP:
            return guiUi.getGuidelines();

        case LIST:
            return guiUi.getTaskListMessage(tasks);

        case MARK:
            return guiUi.getMarkedTaskMessage(tasks.markTask(cmd.index));

        case UNMARK:
            return guiUi.getUnmarkedTaskMessage(tasks.unmarkTask(cmd.index));

        case DELETE:
            return guiUi.getDeleteTaskMessage(tasks.removeTask(cmd.index))
                    + "\n"
                    + guiUi.getTasksSizeMessage(tasks);

        case TODO, DEADLINE, EVENT:
            return guiUi.getAddedTaskMessage(tasks.addTaskFromInput(input))
                    + "\n"
                    + guiUi.getTasksSizeMessage(tasks);

        case FIND:
            String keyword = cmd.taskInput;
            TaskList matchingResults = tasks.findTasks(keyword);
            return guiUi.getMatchedTasks(matchingResults, keyword);

        case INVALID:
            return "Incorrect input. If you need some help, input 'help'.";

        }
        return "Idk man.";
    }
}