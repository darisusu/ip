public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by){
        super(description);
        this.by =by;
    }

    public static Deadline fromInput(String input) throws PeroException{
        if (input.equals("deadline") || !input.contains("/by")) {
            throw new PeroException("Oops! Deadline requires 'deadline [task] /by [time/date]' format, try again!");
        }
        String[] taskDeadline = input.substring(9).split(" /by "); //starts at index 9, remove "deadline "
        if (taskDeadline.length < 2) {
            throw new PeroException("Oops! Deadline requires 'deadline [task] /by [time/date]' format, try again!");
        }
        String task = taskDeadline[0];
        String by = taskDeadline[1];
        return new Deadline(task,by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
