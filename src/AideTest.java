import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AideTest {

    @Test
    public void testAssignTask() {
        // Arrange
        Aide aide = new Aide("A001", "John Doe");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");

        // Act
        aide.assignTask(cleaning);

        // Assert
        List<Task> tasks = aide.getAssignedTasks();
        System.out.println("Assigned Tasks: " + tasks); // Debug Output
        assertEquals(1, tasks.size(), "Aide should have 1 assigned task.");
        assertEquals("Cleaning", tasks.get(0).getType(), "Assigned task should be 'Cleaning'.");
    }

    @Test
    public void testCompleteTaskSuccess() {
        // Arrange
        Aide aide = new Aide("A001", "John Doe");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        aide.assignTask(cleaning);

        // Act
        boolean result = aide.completeTask(cleaning);

        // Assert
        System.out.println("Task Completion Result: " + result); // Debug Output
        assertTrue(result, "Task should be marked as completed successfully.");
        assertTrue(aide.getAssignedTasks().isEmpty(), "Assigned tasks list should be empty after completion.");
    }

    @Test
    public void testCompleteTaskFailure() {
        // Arrange
        Aide aide = new Aide("A001", "John Doe");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        Task cooking = new Task("Cooking", 45, 15.0, "Kitchen task");
        aide.assignTask(cleaning);

        // Act
        boolean result = aide.completeTask(cooking); // Attempt to complete unassigned task

        // Assert
        System.out.println("Task Completion Result: " + result); // Debug Output
        assertFalse(result, "Attempting to complete an unassigned task should return false.");
        assertEquals(1, aide.getAssignedTasks().size(), "Assigned tasks list should still contain 1 task.");
        assertEquals("Cleaning", aide.getAssignedTasks().get(0).getType(), "Remaining task should be 'Cleaning'.");
    }

    @Test
    public void testToStringWithTasks() {
        // Arrange
        Aide aide = new Aide("A001", "John Doe");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        Task cooking = new Task("Cooking", 45, 15.0, "Kitchen task");
        aide.assignTask(cleaning);
        aide.assignTask(cooking);

        // Act
        String details = aide.toString();

        // Assert
        System.out.println("Aide Details:\n" + details); // Debug Output
        assertTrue(details.contains("John Doe"), "Aide details should include the aide's name.");
        assertTrue(details.contains("Cleaning"), "Aide details should include the task 'Cleaning'.");
        assertTrue(details.contains("Cooking"), "Aide details should include the task 'Cooking'.");
        assertTrue(details.contains("$25.0"), "Aide details should include the cost of the 'Cleaning' task.");
        assertTrue(details.contains("$15.0"), "Aide details should include the cost of the 'Cooking' task.");
    }

    @Test
    public void testToStringWithoutTasks() {
        // Arrange
        Aide aide = new Aide("A001", "John Doe");

        // Act
        String details = aide.toString();

        // Assert
        System.out.println("Aide Details (No Tasks):\n" + details); // Debug Output
        assertTrue(details.contains("John Doe"), "Aide details should include the aide's name.");
        assertTrue(details.contains("No tasks assigned."), "Aide details should indicate no tasks are assigned.");
    }
}
