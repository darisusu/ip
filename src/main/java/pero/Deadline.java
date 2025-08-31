package pero;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A Deadline has a description, completion status, and a due date and time.
 */
public class Deadline extends Task {

    /** Date and time task has to be completed by */
    protected LocalDateTime byTimeObj;

    private static final String COMMAND_KEYWORD = "deadline";
    private static final String BY_SEPARATOR = " /by ";

    private static final String DATE_TIME_PATTERN = "yyyy-dd-MM HHmm";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final DateTimeFormatter FINAL_DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, boolean isDone, LocalDateTime byTimeObj){
        super(description, isDone);
        this.byTimeObj = byTimeObj;
    }

    /**
     * Returns new Deadline object.
     *
     * @param input Input from user.
     * @return Deadline deadline.
     * @throws PeroException if wrong format
    */
    public static Deadline fromInput(String input) throws PeroException{
        if (input.equals(COMMAND_KEYWORD) || !input.contains(BY_SEPARATOR)) {
            throw new PeroException("Oops! Deadline requires 'deadline [task] /by [YYYY-DD-MM HHmm]' format, try again!");
        }

        //starts at index 9, remove "deadline "
        String[] taskDeadline = input.substring(COMMAND_KEYWORD.length()+1).split(BY_SEPARATOR);

        if (taskDeadline.length < 2) {
            throw new PeroException("Oops! Deadline requires 'deadline [task] /by [YYYY-DD-MM HHmm]' format, try again!");
        }

        String task = taskDeadline[0].trim(); //trim leading/trailing spaces
        if (task.isEmpty()) {
            throw new PeroException("Task description cannot be empty, try again!");
        }
        String by = taskDeadline[1].trim();

        LocalDateTime byTimeObj;
        try {
            // Convert String "by" to LocalDateTime "byTimeObj" object
            byTimeObj = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeException e) {
            throw new PeroException("Invalid datetime: '" + by + "'. Please follow format: YYYY-DD-MM HHmm ");
        }

        return new Deadline(task,false, byTimeObj);
    }

    @Override
    public String toStorageString() {
        return "D | " + (isDone? "1" : "0") +
                " | " + this.description + " | " +
                this.byTimeObj.format(STORAGE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byTimeObj.format(FINAL_DISPLAY_FORMATTER) + ")";
    }
}
