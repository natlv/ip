public class Todo extends Task {

    /**
     * Constructor for a ToDo task.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo task to a string representation.
     *
     * @return the string representation of the ToDo task
     */
    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }

    /**
     * Converts the ToDo task to a string format suitable for file storage.
     *
     * @return the formatted string for storage
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
