/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command, ResponseCommand {
    private int taskIndex;
    private String response;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param taskIndex the index of the task to delete
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the specified task from the task list.
     * Displays a message to the user confirming the deletion and updates the storage file.
     *
     * @param tasks   the task list from which the task will be deleted
     * @param ui      the user interface to display messages
     * @param storage the storage object to update the tasks file
     * @throws NatsbotException if the task index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NatsbotException {
        if (taskIndex < 0 || taskIndex >= tasks.getTasks().size()) {
            throw new NatsbotException("Invalid task number.");
        }
        Task task = tasks.getTasks().get(taskIndex);
        assert task != null : "Task should not be null";
        int origTaskListSize = tasks.getTasks().size();
        tasks.deleteTask(taskIndex);
        assert tasks.getTasks().size() == origTaskListSize - 1 : "Task should have been deleted";
        response = "Deleted task:\n" + task;
        ui.showTaskDeleted(task, tasks.getTasks().size());
        storage.save(tasks.getTasks());
    }

    /**
     * Returns the response message generated after deleting the task.
     *
     * @return the response message as a string
     */
    @Override
    public String getString() {
        return response;
    }
}
