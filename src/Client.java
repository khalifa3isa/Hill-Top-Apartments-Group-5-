import java.util.ArrayList;
import java.util.List;

/**
 * Represents a client using the services of the system.
 */
public class Client {
    private String clientId;
    private String name;
    private String email;
    private List<Task> taskHistory;

    /**
     * Constructor to initialize a Client.
     *
     * @param clientId Unique ID for the client.
     * @param name     Name of the client.
     * @param email    Email address of the client.
     */
    public Client(String clientId, String name, String email) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.taskHistory = new ArrayList<>();
    }

    /**
     * Assigns a task to the client.
     *
     * @param task The task to assign.
     */
    public void assignTask(Task task) {
        taskHistory.add(task);
        System.out.println("Task assigned to client " + name + ": " + task.getType());
    }

    /**
     * Retrieves the task history of the client.
     *
     * @return List of completed tasks.
     */
    public List<Task> getTaskHistory() {
        return taskHistory;
    }

    /**
     * Calculates the total amount spent by the client based on task history.
     *
     * @return Total cost of all tasks.
     */
    public double getTotalSpent() {
        double total = 0;
        for (Task task : taskHistory) {
            total += task.getCost();
        }
        return total;
    }

    /**
     * Provides a string representation of the client's details and task history.
     *
     * @return A string describing the client and their tasks.
     */
    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Client ID: ").append(clientId).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Email: ").append(email).append("\n");
        details.append("Task History:\n");

        if (taskHistory.isEmpty()) {
            details.append("No tasks completed.\n");
        } else {
            for (Task task : taskHistory) {
                details.append("- ").append(task.getType()).append(" ($").append(task.getCost()).append(")\n");
            }
            details.append("Total Spent: $").append(String.format("%.2f", getTotalSpent())).append("\n");
        }

        return details.toString();
    }
}
