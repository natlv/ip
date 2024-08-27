public class Todo extends Task {
    protected String taskType;
    public Todo(String description) {
        super(description);
        this.taskType = "[T]";
    }

    @Override
    public String toString() {
        return "  " + taskType + super.getStatusIcon() + super.description;
    }
}
