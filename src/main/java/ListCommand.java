/**
 * Represents a command to list all existing tasks that are in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   the task list containing the tasks to be displayed
     * @param ui      the user interface to display the list of tasks
     * @param storage the storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
