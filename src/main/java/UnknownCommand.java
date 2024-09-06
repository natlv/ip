/**
 * Represents a command to handle unrecognized or invalid user input.
 */
public class UnknownCommand implements Command, ResponseCommand {
    private final String response;

    /**
     * Constructs an UnknownCommand with the specified response message.
     *
     * @param response the response message to display when an unknown command is encountered
     */
    public UnknownCommand(String response) {
        this.response = response;
    }

    /**
     * Executes the unknown command by displaying the error message to the user.
     *
     * @param tasks   the task list (not used in this command)
     * @param ui      the user interface to display messages
     * @param storage the storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(response); // Optional: Display response through UI
    }

    /**
     * Returns the response message generated when an unknown command is encountered.
     *
     * @return the response message as a string
     */
    @Override
    public String getString() {
        return response;
    }
}
