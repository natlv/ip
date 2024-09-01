import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {
    @Test
    void testInvalidCommandThrowsException() {
        Exception exception = assertThrows(NatsbotException.class, () -> {
            CommandParser.parse("nonsense input");
        });
        assertEquals("I'm sorry, I don't understand that command. Use 'list' to see tasks, 'bye' to exit, 'todo' to add a todo, 'deadline' to add a deadline, 'event' to add an event, 'mark' to mark a task as done, or 'delete' to delete a task.", exception.getMessage(), "Unexpected exception message for invalid command.");
    }

    @Test
    void testParseTodoWithBlankDescriptionThrowsException() {
        Exception exception = assertThrows(NatsbotException.class, () -> {
            CommandParser.parse("todo ");
        });
        assertEquals("The description of a todo cannot be empty. Usage: todo DESCRIPTION", exception.getMessage(), "Unexpected exception message for empty todo.");
    }
}
