import java.util.List;

/**
 * Handles all user interactions for the Natsbot application.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        // Print welcome message
        System.out.println("Hello! I'm Natsbot. What can I do for you?\n" + "Here's the possible ways you can text me:\n"
                + " [] denotes required non-empty fields\n" + " > 'todo [task description]' - adds a to-do task\n"
                + " > 'deadline [task description] /by [due date]' - adds a deadline task\n"
                + "  - The due date should be in the format 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'\n"
                + " > 'event [task description] /from [event start] /to [event end]' - adds an event task\n"
                + " > 'doafter [task description] /after [prerequisite]' - adds a task to be done after something else"
                + " > 'list' - lists all existing tasks\n"
                + " > 'mark [task number]' - marks a task as done\n"
                + " > 'delete [task number]' - deletes a task\n"
                + " > 'find [keyword]' - finds tasks containing the keyword\n"
                + " > 'bye' - exits the program\n");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task the task that was added
     * @param size the current number of tasks in the list
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param response the response message detailing the deleted tasks
     * @param size the current number of tasks in the list
     */
    public void showTaskDeleted(String response, int size) {
        System.out.println(response);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param response the response message detailing the tasks marked as done
     */
    public void showTaskMarked(String response) {
        System.out.println(response);
    }

    /**
     * Displays a list of added tasks to the user.
     *
     * @param tasks the list of existing tasks
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You haven't added any tasks to the list yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays the tasks that match the search keyword to the user.
     *
     * @param foundTasks the list of tasks that match the search keyword
     */
    public void showFoundTasks(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println("No tasks found matching the keyword.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get(i));
            }
        }
    }
}
