/**
 * Represents a task with a description and a completion status.
 * This is an abstract base class for different types of tasks such as Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is done, otherwise a space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Converts the task to a string representation.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to a string format suitable for file storage.
     *
     * @return the formatted string for storage
     */
    public abstract String toFileString();
}
