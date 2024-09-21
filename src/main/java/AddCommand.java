/**
 * Represents a command to add a task to the list of tasks.
 */
public class AddCommand implements Command, ResponseCommand {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task the task to be added to the task list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the specified task to the task list.
     * Displays a message to the user confirming the addition and updates the storage file.
     *
     * @param tasks   the task list to which the task will be added
     * @param ui      the user interface to display messages
     * @param storage the storage object to update the tasks file
     * @throws NatsbotException if there is an error updating the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NatsbotException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTasks().size());
        storage.save(tasks.getTasks());
    }

    @Override
    public String getString() {
        return "Bet. Task added: " + task.toString();
    }
}
