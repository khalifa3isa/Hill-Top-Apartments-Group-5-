
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskInitializationValidInputs() {
        // Arrange & Act
        Task task = new Task("Cleaning", 60, 25.0, "Living room cleaning");

        // Assert
        assertEquals("Cleaning", task.getType());
        assertEquals(60, task.getDuration());
        assertEquals(25.0, task.getCost(), 0.01);
        assertEquals("Clean the kitchen and bathroom", task.toString());
        assertFalse(task.isCompleted());
    }

    @Test
    public void testTaskInitializationInvalidInputs() {
        // Arrange, Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Cleaning", -60, 25.0, "Living room cleaning");
        });
        assertEquals("Invalid task details: Duration and cost must be positive.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Cleaning", 60, -25.0, "Living room cleaning");
        });
        assertEquals("Invalid task details: Duration and cost must be positive.", exception.getMessage());
    }

    @Test
    public void testMarkCompleted() {
        // Arrange
        Task task = new Task("Laundry", 45, 15.0, "Living room cleaning");

        // Act
        task.markCompleted();

        // Assert
        assertTrue(task.isCompleted());
    }

    @Test
    public void testToStringBeforeAndAfterCompletion() {
        // Arrange
        Task task = new Task("Gardening", 120, 50.0, "Living room cleaning");

        // Act
        String beforeCompletion = task.toString();
        task.markCompleted();
        String afterCompletion = task.toString();

        // Assert
        assertTrue(beforeCompletion.contains("Completed: No"));
        assertTrue(afterCompletion.contains("Completed: Yes"));
    }
}
