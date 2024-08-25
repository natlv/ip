public class CommandProcessor {
    public void processInput(String input) throws IncompleteTaskException {
        if (input.equals("todo")) {
            throw new IncompleteTaskException("The 'todo' command requires a description. Usage: todo <description>");
        } else if (input.equals("deadline")) {
            throw new IncompleteTaskException("The 'deadline' command requires a description and a deadline. Usage: deadline <description> /by <deadline>");
        } else if (input.equals("event")) {
            throw new IncompleteTaskException("The 'event' command requires a description and a time range. Usage: event <description> /from <start time> /to <end time>");
        } else {
            throw new IncompleteTaskException("Type 'todo', 'deadline ... /by ...', or 'event ... /from ... /to ...' followed by a task to add it,\n'list' to see tasks, or type 'bye' to exit the program.\n");
        }
    }
}
