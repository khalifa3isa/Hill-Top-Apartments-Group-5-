import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskInitializationValidInputs() {
        // Arrange & Act
        Task task = new Task("Cleaning", 60, 25.0, "Living room cleaning");

        // Assert
        assertEquals("Cleaning", task.getType(), "Task type should match.");
        assertEquals(60, task.getDuration(), "Task duration should match.");
        assertEquals(25.0, task.getCost(), 0.01, "Task cost should match.");
        assertEquals("Task: Cleaning | Duration: 60 mins | Cost: $25.00 | Description: Living room cleaning | Completed: No", task.toString(), "String representation should match.");
        assertFalse(task.isCompleted(), "Task should not be completed initially.");
    }

    @Test
    public void testTaskInitializationInvalidInputs() {
        // Invalid duration
        System.out.println("Testing invalid task duration...");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Cleaning", -60, 25.0, "Living room cleaning");
        });
        System.out.println("Outcome: " + exception.getMessage());
        assertEquals("Invalid task details: Duration must be positive and cost must not be negative.", exception.getMessage());

        // Valid duration
        System.out.println("Testing valid task duration...");
        Task validTask = new Task("Cleaning", 30, 25.0, "Living room cleaning");
        System.out.println("Outcome: Task created successfully: " + validTask);
        assertNotNull(validTask);

        // Invalid cost
        System.out.println("Testing invalid task cost...");
        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Cleaning", 60, -25.0, "Living room cleaning");
        });
        System.out.println("Outcome: " + exception.getMessage());
        assertEquals("Invalid task details: Duration must be positive and cost must not be negative.", exception.getMessage());

        // Valid cost
        System.out.println("Testing valid task cost...");
        Task validCostTask = new Task("Cleaning", 60, 0.0, "Living room cleaning");
        System.out.println("Outcome: Task created successfully: " + validCostTask);
        assertNotNull(validCostTask);

        // Null type
        System.out.println("Testing null task type...");
        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, 60, 25.0, "Living room cleaning");
        });
        System.out.println("Outcome: " + exception.getMessage());
        assertEquals("Task type cannot be null or empty.", exception.getMessage());

        // Valid type
        System.out.println("Testing valid task type...");
        Task validTypeTask = new Task("ValidType", 60, 25.0, "Living room cleaning");
        System.out.println("Outcome: Task created successfully: " + validTypeTask);
        assertNotNull(validTypeTask);

        // Null description
        System.out.println("Testing null task description...");
        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Cleaning", 60, 25.0, null);
        });
        System.out.println("Outcome: " + exception.getMessage());
        assertEquals("Task description cannot be null or empty.", exception.getMessage());

        // Valid description
        System.out.println("Testing valid task description...");
        Task validDescriptionTask = new Task("Cleaning", 60, 25.0, "Valid description");
        System.out.println("Outcome: Task created successfully: " + validDescriptionTask);
        assertNotNull(validDescriptionTask);
    }

    @Test
    public void testMarkCompleted() {
        // Arrange
        Task task = new Task("Laundry", 45, 15.0, "Wash clothes");

        // Act
        task.markCompleted();

        // Assert
        assertTrue(task.isCompleted(), "Task should be marked as completed.");
    }

    @Test
    public void testToStringBeforeAndAfterCompletion() {
        // Arrange
        Task task = new Task("Gardening", 120, 50.0, "Plant flowers");

        // Act
        String beforeCompletion = task.toString();
        task.markCompleted();
        String afterCompletion = task.toString();

        // Assert
        assertTrue(beforeCompletion.contains("Completed: No"), "Before completion, status should be 'No'.");
        assertTrue(afterCompletion.contains("Completed: Yes"), "After completion, status should be 'Yes'.");
    }
}

