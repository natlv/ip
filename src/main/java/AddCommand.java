public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NatsbotException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTasks().size());
        storage.save(tasks.getTasks());
    }
}
