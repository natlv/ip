/**
 * Represents a command that can be executed in the Natsbot application.
 * Each command defines an action that manipulates the task list, interacts with the user interface,
 * and may update the task storage.
 */
public interface Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   the task list on which the command operates
     * @param ui      the user interface for displaying messages to the user
     * @param storage the storage system used to save or load tasks
     * @throws NatsbotException if an error occurs during the execution of the command
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws NatsbotException;
}
