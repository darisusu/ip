public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to){
        super(description, isDone);
        this.to = to;
        this.from = from;
    }

    public static Event fromInput(String input) throws PeroException {
        if (input.equals("event") || !input.contains("/from") || !input.contains("/to")) {
            throw new PeroException("Oops! Event requires 'event [task] /from [time/date] /to [time/date]' format, try again!");
        }
        String[] taskEvent = input.substring(6).split(" /from "); //starts at index 6, remove "event "
        if (taskEvent.length < 2) {
            throw new PeroException("Oops! Event requires 'event [task] /from [time/date] /to [time/date]' format, try again!");
        }
        String task = taskEvent[0];
        String[] time = taskEvent[1].split(" /to ");
        if (time.length < 2) {
            throw new PeroException("Oops! Event requires 'event [task] /from [time/date] /to [time/date]' format, try again!");
        }
        String from = time[0];
        String to = time[1];
        return new Event(task,false,from,to);
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone? "1" : "0") + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }


}
