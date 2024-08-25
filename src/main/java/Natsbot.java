import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Natsbot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Natsbot\n" + "What can I do for you?\n");

        System.out.println("Type a prompt below or type 'bye' to exit the program.\n");

        Scanner reader = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();

        while(true) {
            String input = reader.nextLine();

            if (Objects.equals(input, "bye")) {
                System.out.println("Goodbye. Hope to see you again soon!");
                break;
            } else if (Objects.equals(input, "list")) {
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println(i + 1 + ". " + inputs.get(i));
                }
            } else {
                inputs.add(input);
                System.out.println("added: " + input);
            }
        }

        reader.close();
    }
}
