import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testValidEventCreation() {
        // Test creation of a new Event task
        Event event = new Event("Project meeting", "9am", "9pm");
        assertEquals("  [E][ ] Project meeting (from: 9am to: 9pm)", event.toString(), "Event string representation mismatch.");
    }
}
