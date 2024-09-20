import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the {@link CommandParser} class.
 * Ensures correct behavior when parsing commands, including exception handling for invalid inputs.
 */
public class CommandParserTest {

    /**
     * Tests that parsing a todo command with a blank description throws a NatsbotException
     * with the expected error message.
     */
    @Test
    void testTodoWithBlankDescriptionThrowsException() {
        Exception exception = assertThrows(NatsbotException.class, () -> {
            CommandParser.parse("todo ");
        });
        assertEquals("The description of a todo cannot be empty. Usage: todo DESCRIPTION", exception.getMessage(), "Unexpected exception message for empty todo.");
    }
}
