import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SchedulerTest {

    @Test
    public void testScheduleTask() {
        // Arrange
        Scheduler scheduler = new Scheduler();
        Aide aide = new Aide("A001", "John Doe");
        Client client = new Client("C001", "Jane Smith", "jane.smith@example.com");
        Task task = new Task("Cleaning", 60, 25.0, "Living room cleaning");

        // Act
        scheduler.scheduleTask(task, aide, client);

        // Assert
        List<Task> aideTasks = scheduler.getTasksForAide(aide);
        List<Task> clientTasks = scheduler.getTasksForClient(client);

        assertEquals(1, aideTasks.size(), "Aide should have 1 scheduled task.");
        assertEquals("Cleaning", aideTasks.get(0).getType(), "Scheduled task for aide should be 'Cleaning'.");
        assertEquals(1, clientTasks.size(), "Client should have 1 scheduled task.");
        assertEquals("Cleaning", clientTasks.get(0).getType(), "Scheduled task for client should be 'Cleaning'.");
    }

    @Test
    public void testScheduleNullTask() {
        // Arrange
        Scheduler scheduler = new Scheduler();
        Aide aide = new Aide("A001", "John Doe");
        Client client = new Client("C001", "Jane Smith", "jane.smith@example.com");

        // Act & Assert
        Exception exception = assertThrows(NullPointerException.class, () -> {
            scheduler.scheduleTask(null, aide, client);
        });

        assertEquals("Task cannot be null.", exception.getMessage(),
                "Scheduling a null task should throw a NullPointerException with the correct message.");
    }

    @Test
    public void testScheduleDuplicateTask() {
        // Arrange
        Scheduler scheduler = new Scheduler();
        Aide aide = new Aide("A001", "John Doe");
        Client client = new Client("C001", "Jane Smith", "jane.smith@example.com");
        Task task = new Task("Cleaning", 60, 25.0, "Living room cleaning");

        // Act
        scheduler.scheduleTask(task, aide, client);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scheduler.scheduleTask(task, aide, client); // Duplicate task scheduling
        });

        // Assert
        assertEquals("Task already scheduled for this aide and client.", exception.getMessage(),
                "Attempting to schedule the same task twice should throw an IllegalArgumentException.");
    }

    @Test
    public void testRetrieveTasksForUnregisteredAide() {
        // Arrange
        Scheduler scheduler = new Scheduler();
        Aide unregisteredAide = new Aide("A002", "Jane Doe");

        // Act
        List<Task> tasks = scheduler.getTasksForAide(unregisteredAide);

        // Assert
        assertTrue(tasks.isEmpty(), "Unregistered aide should have no tasks assigned.");
    }

    @Test
    public void testRetrieveTasksForUnregisteredClient() {
        // Arrange
        Scheduler scheduler = new Scheduler();
        Client unregisteredClient = new Client("C002", "Alice", "alice@example.com");

        // Act
        List<Task> tasks = scheduler.getTasksForClient(unregisteredClient);

        // Assert
        assertTrue(tasks.isEmpty(), "Unregistered client should have no tasks assigned.");
    }

    @Test
    public void testScheduleConflictingTasks() {
        // Arrange
        Scheduler scheduler = new Scheduler();
        Aide aide = new Aide("A001", "John Doe");
        Client client1 = new Client("C001", "Jane Smith", "jane.smith@example.com");
        Client client2 = new Client("C002", "Alice", "alice@example.com");

        Task task1 = new Task("Gardening", 120, 30.0, "Trim the hedges");
        Task task2 = new Task("Laundry", 120, 15.0, "Wash clothes"); // Same duration as task1

        // Act
        scheduler.scheduleTask(task1, aide, client1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scheduler.scheduleTask(task2, aide, client2); // Overlapping task for the same aide
        });

        // Assert
        assertEquals("Task conflicts with an existing schedule for the aide.", exception.getMessage(),
                "Scheduling a conflicting task for the same aide should throw an IllegalArgumentException.");
    }

    @Test
    public void testToStringWithSchedules() {
        // Arrange
        Scheduler scheduler = new Scheduler();
        Aide aide = new Aide("A001", "John Doe");
        Client client = new Client("C001", "Jane Smith", "jane.smith@example.com");

        Task task1 = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        Task task2 = new Task("Laundry", 45, 15.0, "Laundry service");

        scheduler.scheduleTask(task1, aide, client);
        scheduler.scheduleTask(task2, aide, client);

        // Act
        String scheduleDetails = scheduler.toString();

        // Assert
        assertTrue(scheduleDetails.contains("John Doe"), "Schedule should include the aide's name.");
        assertTrue(scheduleDetails.contains("Jane Smith"), "Schedule should include the client's name.");
        assertTrue(scheduleDetails.contains("Cleaning"), "Schedule should include the task 'Cleaning'.");
        assertTrue(scheduleDetails.contains("Laundry"), "Schedule should include the task 'Laundry'.");
    }

    @Test
    public void testToStringNoSchedules() {
        // Arrange
        Scheduler scheduler = new Scheduler();

        // Act
        String scheduleDetails = scheduler.toString();

        // Assert
        assertTrue(scheduleDetails.contains("Aide Schedules:"), "Schedule should include 'Aide Schedules' header.");
        assertTrue(scheduleDetails.contains("Client Schedules:"), "Schedule should include 'Client Schedules' header.");
    }

    @Test
    public void testMultipleAidesAndClients() {
        // Arrange
        Scheduler scheduler = new Scheduler();
        Aide aide1 = new Aide("A001", "John Doe");
        Aide aide2 = new Aide("A002", "Jane Doe");
        Client client1 = new Client("C001", "Alice", "alice@example.com");
        Client client2 = new Client("C002", "Bob", "bob@example.com");

        Task task1 = new Task("Gardening", 90, 30.0, "Trim the hedges");
        Task task2 = new Task("Tutoring", 120, 50.0, "Math session");

        // Act
        scheduler.scheduleTask(task1, aide1, client1);
        scheduler.scheduleTask(task2, aide2, client2);

        List<Task> aide1Tasks = scheduler.getTasksForAide(aide1);
        List<Task> client1Tasks = scheduler.getTasksForClient(client1);
        List<Task> aide2Tasks = scheduler.getTasksForAide(aide2);
        List<Task> client2Tasks = scheduler.getTasksForClient(client2);

        // Assert
        assertEquals(1, aide1Tasks.size(), "Aide 1 should have 1 task assigned.");
        assertEquals("Gardening", aide1Tasks.get(0).getType(), "Aide 1's task should be 'Gardening'.");
        assertEquals(1, client1Tasks.size(), "Client 1 should have 1 task assigned.");
        assertEquals("Gardening", client1Tasks.get(0).getType(), "Client 1's task should be 'Gardening'.");

        assertEquals(1, aide2Tasks.size(), "Aide 2 should have 1 task assigned.");
        assertEquals("Tutoring", aide2Tasks.get(0).getType(), "Aide 2's task should be 'Tutoring'.");
        assertEquals(1, client2Tasks.size(), "Client 2 should have 1 task assigned.");
        assertEquals("Tutoring", client2Tasks.get(0).getType(), "Client 2's task should be 'Tutoring'.");
    }
}
