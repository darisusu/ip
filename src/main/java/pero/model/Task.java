package pero.model;

import pero.PeroException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Generic task in task management system
 * Abstract base class for ToDo, Deadline, Event subclasses to extend
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static final String DATE_TIME_PATTERN = "yyyy-dd-MM HHmm";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);


    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    //may not need to use
    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Parses string input of time and date to LocalDatetime datetime object
     *
     * @param input String date time format from storage.
     * @return LocalDateTime
     * @throws PeroException If invalid date time format.
     */

    public static LocalDateTime parseDateTime(String input) throws PeroException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMATTER);
        } catch (DateTimeException e) {
            throw new PeroException("Invalid datetime: '" + input + "'. Please follow format: YYYY-DD-MM HHmm");
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toStorageString();
}
