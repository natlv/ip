import java.util.List;

/**
 * Represents a command to list all existing tasks that are in the task list.
 */
public class ListCommand implements Command, ResponseCommand {

    private String response;

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   the task list containing the tasks to be displayed
     * @param ui      the user interface to display the list of tasks
     * @param storage the storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();
        StringBuilder responseBuilder = new StringBuilder();

        if (taskList.isEmpty()) {
            responseBuilder.append("Hmm... your task list is empty. There's nothing to see here.");
        } else {
            assert taskList.size() > 0 : "Task list should not be empty.";
            responseBuilder.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                responseBuilder.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        response = responseBuilder.toString();
        ui.showTaskList(tasks.getTasks());
    }

    /**
     * Returns the response message generated after listing tasks.
     *
     * @return the response message as a string
     */
    @Override
    public String getString() {
        return response;
    }
}
