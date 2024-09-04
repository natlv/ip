import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testValidTodoCreation() {
        // Test creation of a new ToDo task
        ToDo todo = new ToDo("Borrow book");
        assertEquals("  [T][ ] Borrow book", todo.toString(), "ToDo string representation mismatch.");
    }

    @Test
    public void testMarkTodoAsCompleted() {
        // Test marking a ToDo task as done
        ToDo todo = new ToDo("Return book");
        todo.setDone(true);
        assertEquals("  [T][X] Return book", todo.toString(), "Marking the ToDo as done was unsuccessful.");
    }
}
