import javafx.application.Platform;

/**
 * Represents a command to terminate the Natsbot application.
 */
public class ExitCommand implements Command, ResponseCommand {

    private String response;

    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param tasks   the task list (not used in this command)
     * @param ui      the user interface to display messages
     * @param storage the storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        response = "Goodbye. Hope to see you again soon!";
        ui.showGoodbyeMessage();
        Platform.exit();
    }

    /**
     * Returns the response message generated after executing the exit command.
     *
     * @return the response message as a string
     */
    @Override
    public String getString() {
        return response;
    }
}
