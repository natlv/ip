import java.util.List;

/**
 * Represents a command to find tasks that match a keyword in their descriptions.
 */
public class FindCommand implements Command, ResponseCommand {
    private final String keyword;
    private String response;

    /**
     * Constructs a FindCommand with the specified keyword to search for.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     * Displays the matching tasks to the user.
     *
     * @param tasks   the task list in which to conduct the search
     * @param ui      the user interface to display the search results
     * @param storage the storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> foundTasks = tasks.findTasksByKeyword(keyword);
        StringBuilder responseBuilder = new StringBuilder();

        if (foundTasks.isEmpty()) {
            responseBuilder.append("No tasks found matching the keyword: ").append(keyword);
        } else {
            assert foundTasks.size() > 0 : "Found tasks list should not be empty.";
            responseBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                responseBuilder.append((i + 1)).append(". ").append(foundTasks.get(i)).append("\n");
            }
        }
        response = responseBuilder.toString();
        ui.showFoundTasks(foundTasks);
    }

    /**
     * Returns the response message generated after searching for tasks.
     *
     * @return the response message as a string
     */
    @Override
    public String getString() {
        return response;
    }
}
