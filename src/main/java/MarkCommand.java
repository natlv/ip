public class MarkCommand implements Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NatsbotException {
        if (taskIndex < 0 || taskIndex >= tasks.getTasks().size()) {
            throw new NatsbotException("Invalid task number.");
        }
        Task task = tasks.getTasks().get(taskIndex);
        task.setDone(true);
        ui.showTaskMarked(task);
        storage.save(tasks.getTasks());
    }
}
