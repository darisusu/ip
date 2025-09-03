package pero;

/**
 * Command type
 */
public class Command {
    public final CommandType type;
    public final int index;          // For MARK, UNMARK, DELETE
    public final String taskInput;   // For TODO, DEADLINE, EVENT

    /**
     * Constructor of command object
     *
     * @param type Command type : BYE, HELP, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, INVALID.
     * @param index Index of curr task in relation to raw task list.
     * @param taskInput Current raw user line input.
     */
    public Command(CommandType type, int index, String taskInput) {
        this.type = type;
        this.index = index;
        this.taskInput = taskInput;
    }

    // For commands without index/taskInput
    public Command(CommandType type) {
        // calls constructor for normal indexed type, but with -1 as placeholder
        this(type, -1, null);
    }
}

