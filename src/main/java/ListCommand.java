import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        if (taskList.isEmpty()) {
            response = "Hmm... your task list is empty. There's nothing to see here.";
        } else {
            assert taskList.size() > 0 : "Task list should not be empty.";
            response = "Here are the tasks in your list:\n" +
                       IntStream.range(0, taskList.size())
                                .mapToObj(i -> (i + 1) + ". " + taskList.get(i))
                                .collect(Collectors.joining("\n"));
        }
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
