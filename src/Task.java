/**
 * Represents a task assigned to an aide or completed by a client.
 */
public class Task {
    private String type;
    private int duration; // Duration in minutes
    private double cost;
    private String description;
    private boolean completed;

    /**
     * Constructor to initialize a Task with basic details.
     *
     * @param type        The type of task (e.g., "Cleaning", "Laundry").
     * @param duration    Duration of the task in minutes.
     * @param cost        Cost of the task.
     * @param description Description of the task.
     * @throws IllegalArgumentException if any input is invalid.
     */
    public Task(String type, int duration, double cost, String description) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Task type cannot be null or empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be null or empty.");
        }
        if (duration <= 0 || cost < 0) {
            throw new IllegalArgumentException("Invalid task details: Duration must be positive and cost must not be negative.");
        }
        this.type = type;
        this.duration = duration;
        this.cost = cost;
        this.description = description;
        this.completed = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Gets the type of the task.
     *
     * @return Task type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the duration of the task.
     *
     * @return Task duration in minutes.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the cost of the task.
     *
     * @return Task cost.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Provides a string representation of the task details.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        return "Task: " + type +
                " | Duration: " + duration + " mins" +
                " | Cost: $" + String.format("%.2f", cost) +
                " | Description: " + description +
                " | Completed: " + (completed ? "Yes" : "No");
    }
}
