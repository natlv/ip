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

        case "do-after":
            return parseDoAfterCommand(words);

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
                    + "'do-after' to add a do-after task, 'mark' to mark a task done, or 'delete' to delete a task.");
        }
    }

    /**
     * Parses the 'mark' command to create a MarkCommand with the specified index.
     *
     * @param words the split input string containing the command and index
     * @return a MarkCommand object to mark a task as completed
     * @throws NatsbotException if the task index is not specified or invalid
     */
    private static Command parseMarkCommand(String[] words) throws NatsbotException {
        if (words.length < 2) {
            throw new NatsbotException("Please specify a task number to mark as done. usage: mark INDEX");
        }
        int markIndex = parseIndex(words[1]);
        return new MarkCommand(markIndex);
    }

    /**
     * Parses the 'delete' command to create a DeleteCommand with the specified index.
     *
     * @param words the split input string containing the command and index
     * @return a DeleteCommand object to delete a task
     * @throws NatsbotException if the task index is not specified or invalid
     */
    private static Command parseDeleteCommand(String[] words) throws NatsbotException {
        if (words.length < 2) {
            throw new NatsbotException("Please specify a task number to delete. usage: delete INDEX");
        }
        int deleteIndex = parseIndex(words[1]);
        return new DeleteCommand(deleteIndex);
    }

    /**
     * Parses the 'todo' command to create an AddCommand that adds a new ToDo task.
     *
     * @param words the split input string containing the command and task description
     * @return an AddCommand object to add a new ToDo task
     * @throws NatsbotException if the task description is missing or empty
     */
    private static Command parseToDoCommand(String[] words) throws NatsbotException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new NatsbotException("The description of a todo cannot be empty. Usage: todo DESCRIPTION");
        }
        Task todo = new ToDo(words[1].trim());
        return new AddCommand(todo);
    }

    /**
     * Parses the 'deadline' command to create an AddCommand that adds a new Deadline task.
     *
     * @param words the split input string containing the command, task description, and date
     * @return an AddCommand object to add a new Deadline task
     * @throws NatsbotException if the command format is incorrect or the description is empty
     */
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

    /**
     * Parses the 'event' command to create an AddCommand that adds a new Event task.
     *
     * @param words the split input string containing the command, task description, and time range
     * @return an AddCommand object to add a new Event task
     * @throws NatsbotException if the command format is incorrect, missing start or end time, or description
     */
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

    /**
     * Parses the provided input string to convert it to a zero-based index.
     *
     * @param input the user's input string representing the index
     * @return the zero-based index parsed from the input
     * @throws NatsbotException if the input is not a valid number or the index is out of bounds
     */
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

    /**
     * Parses the deadline date string and converts it to a Deadline object.
     *
     * @param description the description of the task
     * @param by          the date string representing the deadline
     * @return a Deadline object with the parsed description and date
     * @throws NatsbotException if the date format is incorrect
     */
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
                throw new NatsbotException("Invalid date format. Please either input a real date in the following "
                        + "format: 'yyyy-MM-dd', or a real date and time in the following format: 'yyyy-MM-dd HHmm'.");
            }
        }
    }

    /**
     * Parses the 'do after' command to create an AddCommand that adds a new DoAfterTask.
     *
     * @param words The split input string containing the command, task description, and after event/time.
     * @return An AddCommand object to add a new DoAfterTask.
     * @throws NatsbotException if the command format is incorrect or the description is empty.
     */
    private static Command parseDoAfterCommand(String[] words) throws NatsbotException {
        if (words.length < 2 || !words[1].contains("/after")) {
            throw new NatsbotException("The 'do after' command must include a description and an event/time,"
                    + " separated by '/after'. Usage: DESCRIPTION /after EVENT");
        }
        String[] parts = words[1].split(" /after ", 2);
        String description = parts[0].trim();
        String after = parts[1].trim();

        if (description.isEmpty() || after.isEmpty()) {
            throw new NatsbotException("The description and after time/event cannot be empty. Usage: do-after DESCRIPTION /after EVENT");
        }

        Task doAfterTask = new DoAfter(description, after);
        return new AddCommand(doAfterTask);
    }
}