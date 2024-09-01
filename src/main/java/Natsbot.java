import java.util.Scanner;

public class Natsbot {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Natsbot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

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

    public static void main(String[] args) {
        new Natsbot("data/natsbot.txt").run();
    }
}
