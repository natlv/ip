import java.util.ArrayList;
import java.util.List;

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
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
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
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
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
