import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link ToDo} class.
 * Verifies the correct creation, string representation, and status updating of ToDo tasks.
 */
public class ToDoTest {

    /**
     * Tests the creation of a valid ToDo task and verifies its string representation.
     */
    @Test
    public void testValidTodoCreation() {
        // Test creation of a new ToDo task
        ToDo todo = new ToDo("Borrow book");
        assertEquals("  [T][ ] Borrow book", todo.toString(), "ToDo string representation mismatch.");
    }

    /**
     * Tests marking a ToDo task as completed and verifies the updated string representation.
     */
    @Test
    public void testMarkTodoAsCompleted() {
        // Test marking a ToDo task as done
        ToDo todo = new ToDo("Return book");
        todo.setDone(true);
        assertEquals("  [T][X] Return book", todo.toString(), "Marking the ToDo as done was unsuccessful.");
    }
}
