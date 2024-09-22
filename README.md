# Natsbot 🚀

> "Don't let your dreams be dreams" - Shia LaBeouf ([source](https://www.youtube.com/watch?v=5-sfG8BV8wU))

The project implements a chatbot that assists users with managing their tasks, offering a streamlined interface for task management. This bot:
- increases efficiency
- helps remember tasks
- is super ~~complicated~~ *easy* to learn and use

**These Are the Features:**
- [x] Adding and Deleting Tasks
- [x] Marking Tasks as Completed
- [ ] GUI and frontend

**How to Use the Features:**
1. **Add a Task:** Use the `todo TASK_NAME`, `deadline TASK_NAME /by DATE (yyyyMMdd HHmm or yyyyMMdd)`, or `event TASK_NAME /from START /to END` command.
2. **Complete a Task:** Mark tasks as done with the `mark TASK_NUMBER` command.
3. **Delete a Task:** Remove tasks using the `delete TASK_NAME` command.

The location for storing tasks can be changed easily in `Natsbot.java` by editing the `main` method, which is displayed below:
```java
public static void main(String[] args) {
    new Natsbot("data/natsbot.txt").run();
}
```
