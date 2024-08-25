import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Natsbot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Natsbot\n" + "What can I do for you?\n");

        System.out.println("Type a prompt below or type 'bye' to exit the program.\n");

        Scanner reader = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while(true) {
            String input = reader.nextLine();

            String regexMark = "^mark \\d+$";
            Pattern pattern = Pattern.compile(regexMark);
            Matcher matcher = pattern.matcher(input);

            if (Objects.equals(input, "bye")) {
                // Ends the current conversation
                System.out.println("Goodbye. Hope to see you again soon!");
                break;
            } else if (Objects.equals(input, "list")) {
                // Displays the list of tasks
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println(i + 1 + ". [" + task.getStatusIcon() + "] " + task.description);
                }
            } else if (matcher.matches()){
                // Marks a specific task as done
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index <= tasks.size() && index > 0) {
                    Task task = tasks.get(index);
                    task.isDone = true;
                    System.out.println("Cool! I've marked this task as done:\n" + "[" + task.getStatusIcon() + "] " + task.description);
                } else {
                    System.out.println("Invalid task number.\n");
                }
            } else {
                // Adds a new task to the list of tasks
                Task additionalTask = new Task(input);
                tasks.add(additionalTask);
                System.out.println("added: " + input);
            }
        }

        reader.close();
    }
}
