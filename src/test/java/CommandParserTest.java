import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {
    @Test
    void testTodoWithBlankDescriptionThrowsException() {
        Exception exception = assertThrows(NatsbotException.class, () -> {
            CommandParser.parse("todo ");
        });
        assertEquals("The description of a todo cannot be empty. Usage: todo DESCRIPTION", exception.getMessage(), "Unexpected exception message for empty todo.");
    }
}
