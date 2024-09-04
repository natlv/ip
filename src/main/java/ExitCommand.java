/**
 * Represents a command to terminate the Natsbot application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param tasks   the task list (not used in this command)
     * @param ui      the user interface to display messages
     * @param storage the storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }
}
