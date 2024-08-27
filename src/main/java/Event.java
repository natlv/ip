public class Event extends Task {
    protected String from;
    protected String to;
    protected String taskType;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "[E]";
    }

    @Override
    public String toString() {
        return "  " + taskType + super.getStatusIcon() + super.description + " (from: " + from + " to: " + to + ")";
    }
}
