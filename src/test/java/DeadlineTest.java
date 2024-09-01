import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testValidDeadlineCreation() {
        // Test creation of a new Deadline task
        Deadline deadline = new Deadline("Return book","2024-09-30");
        assertEquals("  [D][ ] Return book (by: 2024-09-30)", deadline.toString(), "Deadline string representation mismatch.");
    }
}
