import java.util.Scanner;

/**
 * Class that runs the Natsbot text application.
 */
public class Natsbot {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private String commandType;

    /**
     * Constructs a new Natsbot instance with the specified file path for storage.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Natsbot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Starts the Natsbot application, handling user input until the "bye" command input is given.
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner reader = new Scanner(System.in);

        while (true) {
            String input = reader.nextLine();
            String response = getResponse(input); // Use getResponse to handle input and generate response
            System.out.println(response);

            if ("ExitCommand".equals(commandType)) { // Break the loop if the command type is ExitCommand
                break;
            }
        }

        reader.close();
        ui.showGoodbyeMessage();
    }

    /**
     * Processes the input, executes the corresponding command, and returns the response.
     *
     * @param input the user's input string
     * @return the response message from the executed command
     */
    public String getResponse(String input) {
        try {
            Command command = CommandParser.parse(input);
            command.execute(tasks, ui, storage);
            commandType = command.getClass().getSimpleName(); // Track the type of the executed command
            return command instanceof ResponseCommand ? ((ResponseCommand) command).getString() : ""; // Get the response string if available
        } catch (NatsbotException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Returns the type of the last executed command.
     *
     * @return the command type as a string
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        ui.showWelcomeMessage();
    }

    /**
     * Returns the welcome message as a string.
     *
     * @return the welcome message
     */
    public String getWelcomeMessage() {
        //@@author natlv-reused
        //Reused from https://github.com/Yukun99
        //with some modifications
        return "Hello! I'm Natsbot. What can I do for you?\n" + "Here's the possible ways you can text me:\n"
                + " [] denotes required non-empty fields\n" + " > 'todo [task description]' - adds a to-do task\n"
                + " > 'deadline [task description] /by [due date]' - adds a deadline task\n"
                + "  - The due date should be in the format 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'\n"
                + " > 'event [task description] /from [event start] /to [event end]' - adds an event task\n"
                + " > 'do-after [description] /after [prerequisite]' - adds a task to be done after something else\n"
                + " > 'list' - lists all existing tasks\n"
                + " > 'mark [task number]' - marks a task as done\n"
                + " > 'delete [task number]' - deletes a task\n"
                + " > 'find [keyword]' - finds tasks containing the keyword\n"
                + " > 'bye' - exits the program\n";
    }

    /**
     * Main method to launch the Natsbot application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Natsbot("data/natsbot.txt").run();
    }
}
