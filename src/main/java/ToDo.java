public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo fromInput(String input) throws PeroException {
        if (input.equals("todo")) {
            throw new PeroException("Oops! ToDo requires 'todo [task]' format, try again!");
        }
        String taskToDo = input.substring(5); //starts at index 5, remove "todo "

        if (taskToDo.isBlank()) {
            throw new PeroException("Oops! ToDo requires 'todo [task]' format, try again!");
        }
        return new ToDo(taskToDo);
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
