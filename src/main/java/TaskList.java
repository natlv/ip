import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the list of tasks for the Natsbot application.
 * The TaskList class provides methods to add, delete, and mark tasks as done,
 * as well as retrieve the current list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index the index of the task to be deleted (0-based index)
     * @throws NatsbotException if the index is out of bounds
     */
    public void deleteTask(int index) throws NatsbotException {
        if (index < 0 || index >= tasks.size()) {
            throw new NatsbotException("Invalid task number.");
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index the index of the task to be marked as done
     * @throws NatsbotException if the index is out of bounds
     */
    public void markTask(int index) throws NatsbotException {
        if (index < 0 || index >= tasks.size()) {
            throw new NatsbotException("Invalid task number.");
        }
        tasks.get(index).setDone(true);
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their descriptions.
     *
     * @param keyword the keyword to search for in task descriptions
     * @return a list of tasks that match the keyword
     */
    public List<Task> findTasksByKeyword(String keyword) {
        return tasks.stream()
                .filter(task -> task.description.contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns the list of tasks.
     *
     * @return the current list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
