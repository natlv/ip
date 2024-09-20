import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for the {@link Deadline} class.
 * Verifies the correct creation and string representation of a Deadline task.
 */
public class DeadlineTest {

    /**
     * Tests the creation of a valid Deadline task and verifies its string representation.
     */
    @Test
    public void testValidDeadlineCreation() {
        // Test creation of a new Deadline task
        Deadline deadline = new Deadline("Return book","2024-09-30");
        assertEquals("  [D][ ] Return book (by: 2024-09-30)", deadline.toString(), "Deadline string representation mismatch.");
    }
}
