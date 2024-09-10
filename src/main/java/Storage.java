import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to and from a file.
 * The Storage class is responsible for reading tasks from a specified file and writing
 * tasks back to the file, allowing the application's task list to persist between sessions.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Reads each line from the file and parses it into a Task object, adding it to the task list.
     *
     * @return a list of tasks loaded from the file
     * @throws NatsbotException if an I/O error occurs while reading the file
     */
    public List<Task> load() throws NatsbotException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(TaskInStorageParser.parse(line));
            }
        } catch (IOException e) {
            throw new NatsbotException("Unable to read file.");
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file specified by the file path.
     * Writes each task in the list to the file, converting them to a suitable string format.
     *
     * @param tasks the list of tasks to save to the file
     * @throws NatsbotException if an I/O error occurs while writing to the file
     */
    public void save(List<Task> tasks) throws NatsbotException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new NatsbotException("Unable to write to file.");
        }
    }
}
