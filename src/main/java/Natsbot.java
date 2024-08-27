import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Natsbot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Natsbot\n" + "What can I do for you?\n");

        System.out.println("Type a prompt below, type 'list' to see tasks,\nor type 'bye' to exit the program.\n");

        Scanner reader = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        String relativePath = "data/natsbot.txt";

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

            String regexDelete = "^delete \\d+$";
            Pattern patternDelete = Pattern.compile(regexDelete);
            Matcher matcherDelete = patternDelete.matcher(input);

            if (Objects.equals(input, "bye")) {
                // End the current conversation
                System.out.println("Goodbye. Hope to see you again soon!");
                break;
            } else if (Objects.equals(input, "list")) {
                // Display the list of tasks
                if (tasks.isEmpty()) {
                    System.out.println("You haven't added any tasks to the list yet!");
                    continue;
                }
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println(i + 1 + task.toString());
                }
            } else if (matcherMark.matches()) {
                // Mark a specific task as done
                int index = Integer.parseInt(input.split(" ")[1]);
                if (index <= tasks.size() && index > 0) {
                    Task task = tasks.get(index - 1);
                    task.isDone = true;
                    System.out.println("Cool! I've marked this task as done:\n" + task);
                } else {
                    System.out.println("Invalid task number.\n");
                }
            } else if (matcherToDo.matches()) {
                // Add a new To Do to the list of tasks
                Todo additionalTask = new Todo(input.substring(5));
                tasks.add(additionalTask);
                System.out.println("Got it. I've added this task:\n" + additionalTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (matcherDeadline.matches()) {
                // Add a new Deadline to the list of tasks
                Deadline additionalTask = new Deadline(input.substring(9, input.indexOf("/by") - 1), input.substring(input.indexOf("/by") + 4));
                tasks.add(additionalTask);
                System.out.println("Got it. I've added this task:\n" + additionalTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (matcherEventTime.matches()) {
                // Add a new Event to the list of tasks
                Event additionalTask = new Event(
                        input.substring(6, input.indexOf("/from") - 1),
                        input.substring(input.indexOf("/from") + 5, input.indexOf("/to") - 1),
                        input.substring(input.indexOf("/to") + 3)
                );
                tasks.add(additionalTask);
                System.out.println("Got it. I've added this task:\n" + additionalTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (Objects.equals(input, "todo") || Objects.equals(input, "deadline") || Objects.equals(input, "event")) {
                // Throw an exception if the user tries to make an empty task without a description
                CommandProcessor processor = new CommandProcessor();
                try {
                    processor.processInput(input);
                } catch (IncompleteTaskException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (matcherDelete.matches()) {
                // Delete selected task from the list
                int index = Integer.parseInt(input.split(" ")[1]);
                if (index <= tasks.size() && index > 0) {
                    Task task = tasks.get(index - 1);
                    tasks.remove(index - 1);
                    System.out.println("Noted. I've removed this task:\n" + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("Invalid task number.\n");
                }
            } else {
                // Throw an exception if the user gives an invalid command
                CommandProcessor processor = new CommandProcessor();
                try {
                    processor.processInput(input);
                } catch (IncompleteTaskException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Handle the case where the data directory does not exist by creating the data directory
            try {
                Files.createDirectories(Paths.get("data"));
            } catch (IOException e) {
                System.err.println("Failed to create directories.");
                e.printStackTrace();
                return;
            }

            // Write tasks to natsbot.txt safely
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(relativePath))) {
                for (Task task : tasks) {
                    if (task instanceof Todo) {
                        writer.write("T | " + (task.isDone ? "1" : "0") + " | " + task.description);
                    } else if (task instanceof Deadline) {
                        writer.write("D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).by);
                    } else if (task instanceof Event) {
                        writer.write("E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Event) task).from + " | " + ((Event) task).to);
                    }
                    writer.newLine();
                }
                System.out.println("Task has been successfully written to the file natsbot.txt under the data folder.");
            } catch (IOException e) {
                // Handle input or output errors in writing to the file
                System.err.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }

        reader.close();
    }
}
