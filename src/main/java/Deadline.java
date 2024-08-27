public class Deadline extends Task {
    protected String by;
    protected String taskType;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "[D]";
    }

    @Override
    public String toString() {
        return "  " + taskType + super.getStatusIcon() + super.description + " (by: " + by + ")";
    }
}
