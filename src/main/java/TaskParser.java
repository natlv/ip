public class TaskParser {
    /**
     * Parses a line of text from the storage file and returns the corresponding Task object.
     *
     * @param line the line of text from the storage file
     * @return the parsed Task object
     * @throws NatsbotException if the line cannot be parsed into a valid Task
     */
    public static Task parse(String line) throws NatsbotException {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new NatsbotException("Invalid task format in storage.");
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            ToDo todo = new ToDo(description);
            todo.setDone(isDone);
            return todo;

        case "D":
            if (parts.length < 4) {
                throw new NatsbotException("Invalid deadline format in storage.");
            }
            String by = parts[3];
            Deadline deadline = new Deadline(description, by);
            deadline.setDone(isDone);
            return deadline;

        case "E":
            if (parts.length < 5) {
                throw new NatsbotException("Invalid event format in storage.");
            }
            String from = parts[3];
            String to = parts[4];
            Event event = new Event(description, from, to);
            event.setDone(isDone);
            return event;

        default:
            throw new NatsbotException("Unknown task type in storage.");
        }
    }
}
