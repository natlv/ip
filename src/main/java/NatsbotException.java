/**
 * Represents an exception specific to the Natsbot application.
 * This exception is used to handle errors that occur within the application's logic,
 * such as invalid task operations or input errors.
 */
public class NatsbotException extends Exception {
    /**
     * Constructs a new NatsbotException with the specified detail message.
     *
     * @param message the detail message that describes the cause of the exception
     */
    public NatsbotException(String message) {
        super(message);
    }
}
