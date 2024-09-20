import java.util.List;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand implements Command, ResponseCommand {
    private final List<Integer> taskIndices;
    private String response;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as done.
     *
     * @param taskIndices the indices of the tasks to be marked as done
     */
    public MarkCommand(List<Integer> taskIndices) {
        this.taskIndices = taskIndices;
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
        StringBuilder responseBuilder = new StringBuilder("Cool! I've marked the following as done:\n");
        for (int index : taskIndices) {
            if (index < 0 || index >= tasks.getTasks().size()) {
                throw new NatsbotException("Invalid task number. Please enter a valid task number.");
            }
            assert tasks != null : "TaskList should not be null";
            Task task = tasks.getTasks().get(index);
            task.setDone(true);
            assert task.getStatusIcon().equals("X") : "Task should be marked as done";
            responseBuilder.append(task).append("\n");
        }
        response = responseBuilder.toString();
        ui.showTaskMarked(response);
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
