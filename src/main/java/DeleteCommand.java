import java.util.Collections;
import java.util.List;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command, ResponseCommand {
    private final List<Integer> taskIndices;
    private String response;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param taskIndices the list of indices of the tasks to be deleted
     */
    public DeleteCommand(List<Integer> taskIndices) {
        this.taskIndices = taskIndices;
        Collections.sort(this.taskIndices, Collections.reverseOrder());
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
        StringBuilder responseBuilder = new StringBuilder("Noted. I've removed the following:\n");
        for (int index : taskIndices) {
            if (index < 0 || index >= tasks.getTasks().size()) {
                throw new NatsbotException("Invalid task number.");
            }
            Task task = tasks.getTasks().get(index);
            assert task != null : "Task should not be null";
            responseBuilder.append(task).append("\n");
            int origTaskListSize = tasks.getTasks().size();
            tasks.deleteTask(index);
            assert tasks.getTasks().size() == origTaskListSize - 1 : "Task should have been deleted";
        }
        response = responseBuilder.toString();
        ui.showTaskDeleted(response, tasks.getTasks().size());
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
