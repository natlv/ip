import java.util.Scanner;

/**
 * Main class that runs the Natsbot application.
 */
public class Natsbot {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
            try {
                Command command = CommandParser.parse(input);
                if (command instanceof ExitCommand) {
                    break;
                }
                command.execute(tasks, ui, storage);
            } catch (NatsbotException e) {
                ui.showError(e.getMessage());
            }
        }

        reader.close();
        ui.showGoodbyeMessage();
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
