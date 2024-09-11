public class DoAfter extends Task {
    private final String after;

    /**
     * Constructs a DoAfterTask with the specified description and after event/time.
     *
     * @param description The description of the task.
     * @param after       The event or time after which the task should be done.
     */
    public DoAfter(String description, String after) {
        super(description);
        this.after = after;
    }

    /**
     * Returns the string representation of the task including the after information.
     *
     * @return A formatted string representing the DoAfterTask.
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after: " + after + ")";
    }

    /**
     * Converts the DoAfterTask to a string format suitable for file storage.
     *
     * @return The formatted string for storage.
     */
    @Override
    public String toFileString() {
        return "A | " + (isDone ? "1" : "0") + " | " + description + " | " + after;
    }
}
