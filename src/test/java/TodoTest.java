import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testValidTodoCreation() {
        // Test creation of a new Todo task
        Todo todo = new Todo("Borrow book");
        assertEquals("  [T][ ] Borrow book", todo.toString(), "Todo string representation mismatch.");
    }

    @Test
    public void testMarkTodoAsCompleted() {
        // Test marking a Todo task as done
        Todo todo = new Todo("Return book");
        todo.setDone(true);
        assertEquals("  [T][X] Return book", todo.toString(), "Marking the Todo as done was unsuccessful.");
    }
}
