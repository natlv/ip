import java.util.List;

public class Ui {
    public void showWelcomeMessage() {
        // Print welcome message
        System.out.println("Hello! I'm Natsbot\n" + "What can I do for you?\n");
        System.out.println("Type a prompt below, type 'list' to see tasks,\nor type 'bye' to exit the program.\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Cool! I've marked this task as done:\n" + task);
    }

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
