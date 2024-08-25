import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Natsbot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Natsbot\n" + "What can I do for you?\n");

        System.out.println("Type a prompt below, type 'list' to see tasks,\nor type 'bye' to exit the program.\n");

        Scanner reader = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while(true) {
            String input = reader.nextLine();

            String regexMark = "^mark \\d+$";
            Pattern patternMark = Pattern.compile(regexMark);
            Matcher matcherMark = patternMark.matcher(input);

            String regexToDo = "^todo .+$";
            Pattern patternToDo = Pattern.compile(regexToDo);
            Matcher matcherToDo = patternToDo.matcher(input);

            String regexDeadline = "^deadline .+ /by .+$";
            Pattern patternDeadline = Pattern.compile(regexDeadline);
            Matcher matcherDeadline = patternDeadline.matcher(input);

            String regexEventTime = "^event .+ /from .+ /to .+$";
            Pattern patternEventTime = Pattern.compile(regexEventTime);
            Matcher matcherEventTime = patternEventTime.matcher(input);

            if (Objects.equals(input, "bye")) {
                // Ends the current conversation
                System.out.println("Goodbye. Hope to see you again soon!");
                break;
            } else if (Objects.equals(input, "list")) {
                // Displays the list of tasks
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println(i + 1 + task.toString());
                }
            } else if (matcherMark.matches()) {
                // Marks a specific task as done
                int index = Integer.parseInt(input.split(" ")[1]);
                if (index <= tasks.size() && index > 0) {
                    Task task = tasks.get(index - 1);
                    task.isDone = true;
                    System.out.println("Cool! I've marked this task as done:\n" + task);
                } else {
                    System.out.println("Invalid task number.\n");
                }
            } else if (matcherToDo.matches()) {
                // Adds a new to do to the list of tasks
                Todo additionalTask = new Todo(input.substring(5));
                tasks.add(additionalTask);
                System.out.println("Got it. I've added this task:\n" + additionalTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (matcherDeadline.matches()) {
                // Adds a new Deadline to the list of tasks
                Deadline additionalTask = new Deadline(input.substring(9, input.indexOf("/by") - 1), input.substring(input.indexOf("/by") + 4));
                tasks.add(additionalTask);
                System.out.println("Got it. I've added this task:\n" + additionalTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (matcherEventTime.matches()) {
                // Adds a new task to the list of tasks
                Event additionalTask = new Event(
                        input.substring(6, input.indexOf("/from") - 1),
                        input.substring(input.indexOf("/from") + 5, input.indexOf("/to") - 1),
                        input.substring(input.indexOf("/to") + 3)
                );
                tasks.add(additionalTask);
                System.out.println("Got it. I've added this task:\n" + additionalTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Type 'todo', 'deadline ... /by ...', or 'event ... from ... to ...' followed by a task to add it,\n'list' to see tasks, or type 'bye' to exit the program.\n");
            }
        }

        reader.close();
    }
}
