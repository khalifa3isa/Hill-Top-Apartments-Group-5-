
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
        assertEquals(1, taskHistory.size());
        assertEquals("Cleaning", taskHistory.get(0).getType());
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
        assertEquals(25.0, totalSpent, 0.01);
    }

    @Test
    public void testGetTotalSpentMultipleTasks() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        Task laundry = new Task("Laundry", 45, 15.0, "Living room cleaning");

        // Act
        client.assignTask(cleaning);
        client.assignTask(laundry);
        double totalSpent = client.getTotalSpent();

        // Assert
        assertEquals(40.0, totalSpent, 0.01);
    }

    @Test
    public void testGetTotalSpentNoTasks() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");

        // Act
        double totalSpent = client.getTotalSpent();

        // Assert
        assertEquals(0.0, totalSpent, 0.01);
    }

    @Test
    public void testToStringWithTasks() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        Task laundry = new Task("Laundry", 45, 15.0, "Living room cleaning");

        client.assignTask(cleaning);
        client.assignTask(laundry);

        // Act
        String details = client.toString();

        // Assert
        assertTrue(details.contains("Jane Doe"));
        assertTrue(details.contains("Cleaning"));
        assertTrue(details.contains("Laundry"));
        assertTrue(details.contains("$25.0"));
        assertTrue(details.contains("$15.0"));
        assertTrue(details.contains("Total Spent: $40.00"));
    }

    @Test
    public void testToStringWithoutTasks() {
        // Arrange
        Client client = new Client("C001", "Jane Doe", "jane.doe@example.com");

        // Act
        String details = client.toString();

        // Assert
        assertTrue(details.contains("Jane Doe"));
        assertTrue(details.contains("No tasks completed."));
    }
}
