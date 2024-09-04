import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws NatsbotException {
        if (index < 0 || index >= tasks.size()) {
            throw new NatsbotException("Invalid task number.");
        }
        tasks.remove(index);
    }

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

    public List<Task> getTasks() {
        return tasks;
    }
}
