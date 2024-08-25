import java.util.Objects;
import java.util.Scanner;

public class Natsbot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Natsbot\n" + "What can I do for you?\n");

        System.out.println("Type a prompt below or type 'bye' to exit the program.\n");
        Scanner reader = new Scanner(System.in);

        while(true) {
            String input = reader.next();

            if (Objects.equals(input, "bye")) {
                System.out.println("Goodbye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
            }
        }

        reader.close();
    }
}
