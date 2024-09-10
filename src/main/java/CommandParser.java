import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses input from the user into executable commands.
 */
public class CommandParser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input the user's input string
     * @return the corresponding Command object
     * @throws NatsbotException if the command is invalid or the input is improperly formatted
     */
    public static Command parse(String input) throws NatsbotException {
        String[] words = input.split(" ", 2);
        String commandType = words[0].toLowerCase();

        switch (commandType) {
        case "list":
            return new ListCommand();

        case "mark":
            return parseMarkCommand(words);

        case "delete":
            return parseDeleteCommand(words);

        case "todo":
            return parseToDoCommand(words);

        case "deadline":
            return parseDeadlineCommand(words);

        case "event":
            return parseEventCommand(words);

        case "find":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new NatsbotException("The search keyword cannot be empty.");
            }
            return new FindCommand(words[1].trim());

        case "bye":
            return new ExitCommand();

        default:
            return new UnknownCommand("I'm sorry, I don't understand that command. Use 'list' to see tasks,"
                    + "'bye' to exit, 'todo' to add a todo, 'deadline' to add a deadline, 'event' to add an event,"
                    + "'mark' to mark a task as done, or 'delete' to delete a task.");
        }
    }

    private static Command parseMarkCommand(String[] words) throws NatsbotException {
        if (words.length < 2) {
            throw new NatsbotException("Please specify a task number to mark as done. usage: mark INDEX");
        }
        int markIndex = parseIndex(words[1]);
        return new MarkCommand(markIndex);
    }

    private static Command parseDeleteCommand(String[] words) throws NatsbotException {
        if (words.length < 2) {
            throw new NatsbotException("Please specify a task number to delete. usage: delete INDEX");
        }
        int deleteIndex = parseIndex(words[1]);
        return new DeleteCommand(deleteIndex);
    }

    private static Command parseToDoCommand(String[] words) throws NatsbotException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new NatsbotException("The description of a todo cannot be empty. Usage: todo DESCRIPTION");
        }
        Task todo = new ToDo(words[1].trim());
        return new AddCommand(todo);
    }

    private static Command parseDeadlineCommand(String[] words) throws NatsbotException {
        if (words.length < 2 || !words[1].contains("/by")) {
            throw new NatsbotException("The deadline command must include a description and a date,"
                    + "separated by '/by'. Usage: deadline DESCRIPTION /by DATE (yyyy-MM-dd) or (yyyy-MM-dd HHmm)");
        }
        assert words != null && words.length > 0 : "Input words should not be null or empty";
        assert words[1].contains("/by") : "Deadline command must contain '/by' separator";
        String[] parts = words[1].split(" /by ", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        Task deadline = parseDeadline(description, by);
        return new AddCommand(deadline);
    }

    private static Command parseEventCommand(String[] words) throws NatsbotException {
        if (words.length < 2 || !words[1].contains("/from") || !words[1].contains("/to")) {
            throw new NatsbotException("The 'event' command must include a description and a time range."
                    + "Usage: event DESCRIPTION /from START /to END");
        }
        String[] parts = words[1].split(" /from | /to ");
        if (parts.length < 3) {
            throw new NatsbotException("The event must have a valid description, start time, and end time."
                    + "Usage: event DESCRIPTION /from START /to END");
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Task event = new Event(description, from, to);
        return new AddCommand(event);
    }

    private static int parseIndex(String input) throws NatsbotException {
        try {
            int index = Integer.parseInt(input) - 1; // Convert to zero-based index
            if (index < 0) {
                throw new NumberFormatException();
            }
            return index;
        } catch (NumberFormatException e) {
            throw new NatsbotException("Invalid task number.");
        }
    }

    private static Deadline parseDeadline(String description, String by) throws NatsbotException {
        try {
            LocalDate date = LocalDate.parse(by);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new Deadline(description, formattedDate);
        } catch (DateTimeParseException e1) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
                return new Deadline(description, formattedDateTime);
            } catch (DateTimeParseException e2) {
                throw new NatsbotException("Invalid date format. Please either input a real date in the following"
                        + "format: 'yyyy-MM-dd', or a real date and time in the following format: 'yyyy-MM-dd HHmm'.");
            }
        }
    }
}