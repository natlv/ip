import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link Event} class.
 * Verifies the correct creation and string representation of an Event task.
 */
public class EventTest {

    /**
     * Tests the creation of a valid Event task and verifies its string representation.
     */
    @Test
    public void testValidEventCreation() {
        // Test creation of a new Event task
        Event event = new Event("Project meeting", "9am", "9pm");
        assertEquals("  [E][ ] Project meeting (from: 9am to: 9pm)", event.toString(), "Event string representation mismatch.");
    }
}
