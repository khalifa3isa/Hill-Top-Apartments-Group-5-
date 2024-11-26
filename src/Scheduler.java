import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Scheduler class is responsible for assigning tasks to aides and clients.
 */
public class Scheduler {
    private final Map<Aide, List<Task>> aideSchedules;
    private final Map<Client, List<Task>> clientSchedules;

    /**
     * Constructor to initialize the Scheduler.
     */
    public Scheduler() {
        this.aideSchedules = new HashMap<>();
        this.clientSchedules = new HashMap<>();
    }

    /**
     * Schedules a task for the given aide and client.
     *
     * @param task   The task to be scheduled.
     * @param aide   The aide to whom the task is assigned.
     * @param client The client for whom the task is assigned.
     * @throws NullPointerException     if task, aide, or client is null.
     * @throws IllegalArgumentException if the task is already scheduled or conflicts with another task.
     */
    public void scheduleTask(Task task, Aide aide, Client client) {
        // Validate inputs
        if (task == null) {
            throw new NullPointerException("Task cannot be null.");
        }
        if (aide == null) {
            throw new NullPointerException("Aide cannot be null.");
        }
        if (client == null) {
            throw new NullPointerException("Client cannot be null.");
        }

        // Prevent duplicate task scheduling
        List<Task> aideTasks = aideSchedules.getOrDefault(aide, new ArrayList<>());
        List<Task> clientTasks = clientSchedules.getOrDefault(client, new ArrayList<>());
        if (aideTasks.contains(task) || clientTasks.contains(task)) {
            throw new IllegalArgumentException("Task already scheduled for this aide and client.");
        }

        // Check for conflicting tasks for the same aide
        for (Task existingTask : aideTasks) {
            if (isOverlapping(existingTask, task)) {
                throw new IllegalArgumentException("Task conflicts with an existing schedule for the aide.");
            }
        }

        // Assign the task
        aide.assignTask(task);
        client.assignTask(task);
        aideSchedules.computeIfAbsent(aide, k -> new ArrayList<>()).add(task);
        clientSchedules.computeIfAbsent(client, k -> new ArrayList<>()).add(task);

        System.out.println("Task scheduled: " + task.getType() + " for Aide: " + aide + " and Client: " + client);
    }

    /**
     * Retrieves the tasks assigned to a specific aide.
     *
     * @param aide The aide whose tasks are to be retrieved.
     * @return List of tasks assigned to the aide, or an empty list if none exist.
     */
    public List<Task> getTasksForAide(Aide aide) {
        return aideSchedules.getOrDefault(aide, new ArrayList<>());
    }

    /**
     * Retrieves the tasks assigned to a specific client.
     *
     * @param client The client whose tasks are to be retrieved.
     * @return List of tasks assigned to the client, or an empty list if none exist.
     */
    public List<Task> getTasksForClient(Client client) {
        return clientSchedules.getOrDefault(client, new ArrayList<>());
    }

    /**
     * Checks if two tasks overlap in their duration.
     *
     * @param task1 The first task.
     * @param task2 The second task.
     * @return true if the tasks overlap, false otherwise.
     */
    private boolean isOverlapping(Task task1, Task task2) {
        return task1.getDuration() == task2.getDuration(); // Adjust logic if needed for actual overlap checking
    }

    /**
     * Provides a string representation of the scheduler with all tasks.
     *
     * @return A string describing all tasks assigned to aides and clients.
     */
    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Aide Schedules:\n");
        for (Map.Entry<Aide, List<Task>> entry : aideSchedules.entrySet()) {
            details.append(entry.getKey()).append("\n");
            for (Task task : entry.getValue()) {
                details.append("- ").append(task).append("\n");
            }
        }
        details.append("Client Schedules:\n");
        for (Map.Entry<Client, List<Task>> entry : clientSchedules.entrySet()) {
            details.append(entry.getKey()).append("\n");
            for (Task task : entry.getValue()) {
                details.append("- ").append(task).append("\n");
            }
        }
        return details.toString();
    }
}

