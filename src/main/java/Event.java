/**
 * Represents a task that starts and ends at a specific time, known as an Event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for an Event task.
     *
     * @param description the task description
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event task to a string representation.
     *
     * @return the string representation of the Event task
     */
    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the Event task to a string format suitable for file storage.
     *
     * @return the formatted string for storage
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
