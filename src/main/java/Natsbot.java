import java.util.Scanner;
import java.io.File;
import java.io.IOException;

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

        ensureDirectoryAndFileExist(filePath);

        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Constructs a new Natsbot instance with the specified file path for storage.
     * Ensures that the necessary directory and file are created if they do not exist.
     *
     * @param filePath the path to the file where tasks are stored
     */
    private void ensureDirectoryAndFileExist(String filePath) {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory: " + directory.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create file: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating the file: " + file.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }

    /**
     * Starts the Natsbot application, handling user input until the "bye" command input is given.
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner reader = new Scanner(System.in);

        while (true) {
            String input = reader.nextLine();
            String response = getResponse(input);
            System.out.println(response);

            if ("ExitCommand".equals(commandType)) {
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
            commandType = command.getClass().getSimpleName();
            return command instanceof ResponseCommand ? ((ResponseCommand) command).getString() : "";
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
     * Credit to user @Yukun99 for the idea of placing allowed commands in the welcome message,
     * so the instructions are clear for the user to follow.
     *
     * @return the welcome message
     */
    public String getWelcomeMessage() {
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
