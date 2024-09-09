/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand implements Command, ResponseCommand {
    private int taskIndex;
    private String response;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as done.
     *
     * @param taskIndex the index of the task to mark as done
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     * Displays a message confirming the task has been marked and updates the storage file.
     *
     * @param tasks   the task list containing the task to be marked as done
     * @param ui      the user interface to display messages
     * @param storage the storage object to update the tasks file
     * @throws NatsbotException if the task index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NatsbotException {
        if (taskIndex < 0 || taskIndex >= tasks.getTasks().size()) {
            throw new NatsbotException("Invalid task number.");
        }
        assert tasks != null : "TaskList should not be null";
        assert taskIndex >= 0 && taskIndex < tasks.getTasks().size() : "Task index should be valid";
        Task task = tasks.getTasks().get(taskIndex);
        task.setDone(true);
        assert task.getStatusIcon().equals("X") : "Task should be marked as done";
        response = "Nice! I've marked this task as done:\n" + task;
        ui.showTaskMarked(task);
        storage.save(tasks.getTasks());
    }

    /**
     * Returns the response message generated after marking the task.
     *
     * @return the response message as a string
     */
    @Override
    public String getString() {
        return response;
    }
}
