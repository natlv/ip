/**
 * Interface for commands that provide a response string after execution.
 */
public interface ResponseCommand {
    /**
     * Returns the response message generated after executing the command.
     *
     * @return the response message as a string
     */
    String getString();
}
