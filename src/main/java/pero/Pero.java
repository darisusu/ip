package pero;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

// can improve polymorphism of each subclass inheriting task
// can include error handling for mark/unmark
// exception index out of bounds for mark/unmark

public class Pero {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        Ui.showWelcome();
        Ui.showGuideLines();


        Storage storage = new Storage("Pero_storage.txt");
        List<Task> tasks = storage.loadList();

        Ui.showTaskList(tasks);

        String input = ""; //initialise input
        while (true) {

            Ui.showPrompt();

            input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;  // exit the loop immediately
            }

            try {
                if (input.equalsIgnoreCase("list")) {
                    Ui.showTaskList(tasks);

                } else if (input.matches("mark (\\d+)")) {
                    // get index by converting string to int
                    // split input and get second part, -1 to match 0-indexing
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task currTask = tasks.get(index);
                    currTask.markAsDone();
                    Ui.showMarkedTask(currTask);

                } else if (input.matches("unmark (\\d+)")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task currTask = tasks.get(index);
                    currTask.markAsUndone();
                    Ui.showUnmarkedTask(currTask);

                } else if (input.matches("delete (\\d+)")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Ui.showDelete(tasks,index);
                    tasks.remove(index);
                    Ui.showTasksSize(tasks);

                } else if (input.startsWith("todo")) {
                    Task currTask = ToDo.fromInput(input);
                    tasks.add(currTask);
                    Ui.showAddedTask(currTask);
                    Ui.showTasksSize(tasks);

                } else if (input.startsWith("deadline")) {
                    Task currTask = Deadline.fromInput(input);
                    tasks.add(currTask);
                    Ui.showAddedTask(currTask);
                    Ui.showTasksSize(tasks);

                } else if (input.startsWith("event")) {
                    Task currTask = Event.fromInput(input);
                    tasks.add(currTask);
                    Ui.showAddedTask(currTask);
                    Ui.showTasksSize(tasks);

                } else {
                    throw new PeroException("Oops! Idk whats that, pls try again.");
                }
            } catch (PeroException e) { //catches all the exception from each fromInput parsing user inputs too
                Ui.showExceptions(e.getMessage());
            }

            Ui.showEmptyLine();
        }

        try {
            Ui.showSavingToStorage(tasks, storage.getFilePath());
            storage.saveList(tasks);
        } catch (IOException e) {
            Ui.showExceptions(e.getMessage());
        }

        Ui.showExit();
    }
}
