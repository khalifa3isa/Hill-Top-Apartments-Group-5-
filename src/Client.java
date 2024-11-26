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
     * @throws IllegalArgumentException if any argument is null or empty.
     */
    public Client(String clientId, String name, String email) {
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("Client ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) { // Added validation
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.taskHistory = new ArrayList<>();
    }

    /**
     * Assigns a task to the client.
     *
     * @param task The task to assign.
     * @throws NullPointerException if the task is null.
     */
    public void assignTask(Task task) {
        if (task == null) {
            throw new NullPointerException("Task cannot be null.");
        }
        taskHistory.add(task);
    }

    /**
     * Retrieves the task history of the client.
     *
     * @return List of completed tasks.
     */
    public List<Task> getTaskHistory() {
        return new ArrayList<>(taskHistory); // Return a defensive copy to protect encapsulation.
    }

    /**
     * Calculates the total amount spent by the client based on task history.
     *
     * @return Total cost of all tasks.
     */
    public double getTotalSpent() {
        return taskHistory.stream().mapToDouble(Task::getCost).sum();
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
                details.append("- ").append(task.getType())
                        .append(" ($").append(String.format("%.2f", task.getCost())).append(")\n");
            }
            details.append("Total Spent: $").append(String.format("%.2f", getTotalSpent())).append("\n");
        }

        return details.toString();
    }

    /**
     * Returns the client's ID.
     *
     * @return Client ID.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Returns the client's name.
     *
     * @return Client name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the client's email.
     *
     * @return Client email.
     */
    public String getEmail() {
        return email;
    }
}
