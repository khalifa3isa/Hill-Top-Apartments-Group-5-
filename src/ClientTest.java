import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    public void testAssignTask() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");

        // Act
        client.assignTask(cleaning);

        // Assert
        List<Task> taskHistory = client.getTaskHistory();
        assertEquals(1, taskHistory.size(), "Task history size should be 1.");
        assertEquals("Cleaning", taskHistory.get(0).getType(), "Task type should match.");
    }

    @Test
    public void testAssignTaskNull() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");

        // Act & Assert
        System.out.println("Testing null task assignment...");
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> client.assignTask(null),
                "Assigning a null task should throw NullPointerException.");
        System.out.println("Caught exception as expected: " + thrown);
    }

    // Sad case: Creating a client with an empty name
    @Test
    public void testClientCreationWithEmptyName() {
        System.out.println("Testing client creation with empty name...");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Client("C002", "", "valid.email@example.com");
        }, "Creating a client with an empty name should throw IllegalArgumentException.");
        System.out.println("Caught exception as expected: " + thrown.getMessage());
    }

    // Sad case: Assigning a task with invalid cost
    @Test
    public void testTaskWithNegativeCost() {
        System.out.println("Testing task creation with negative cost...");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Negative Task", 30, -10.0, "Invalid cost");
        }, "Creating a task with a negative cost should throw IllegalArgumentException.");
        System.out.println("Caught exception as expected: " + thrown.getMessage());
    }

    // Sad case: Retrieving task history from a new client
    @Test
    public void testEmptyTaskHistory() {
        System.out.println("Testing retrieval of task history from a new client...");
        Client client = new Client("C003", "John Doe", "john.doe@example.com");
        List<Task> taskHistory = client.getTaskHistory();
        assertTrue(taskHistory.isEmpty(), "Task history should be empty for a new client.");
        System.out.println("Task history is empty as expected.");
    }

    @Test
    public void testGetTotalSpentSingleTask() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");

        // Act
        client.assignTask(cleaning);
        double totalSpent = client.getTotalSpent();

        // Assert
        assertEquals(25.0, totalSpent, 0.01, "Total spent should equal the task cost.");
    }

    @Test
    public void testGetTotalSpentNoTasks() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");

        // Act
        double totalSpent = client.getTotalSpent();

        // Assert
        assertEquals(0.0, totalSpent, 0.01, "Total spent should be 0.0 when no tasks are assigned.");
    }

    @Test
    public void testToStringWithoutTasks() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");

        // Act
        String details = client.toString();

        // Assert
        assertTrue(details.contains("Jane Doe"), "Output should include client name.");
        assertTrue(details.contains("No tasks completed."), "Output should indicate no tasks completed.");
    }

    // Additional sad cases can be added as needed
}
