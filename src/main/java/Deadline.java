/**
 * Represents a task that should be done by a specific date or date and time, known as a Deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description the task description
     * @param by          the deadline date or time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline task to a string representation.
     *
     * @return the string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the Deadline task to a string format suitable for file storage.
     *
     * @return the formatted string for storage
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
