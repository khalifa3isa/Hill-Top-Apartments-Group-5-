//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AideTest {
    public AideTest() {
    }

    @Test
    public void testAssignTask() {
        Aide aide = new Aide("A001", "John Doe");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        aide.assignTask(cleaning);
        List<Task> tasks = aide.getAssignedTasks();
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertEquals("Cleaning", ((Task)tasks.get(0)).getType());
    }

    @Test
    public void testCompleteTaskSuccess() {
        Aide aide = new Aide("A001", "John Doe");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        aide.assignTask(cleaning);
        boolean result = aide.completeTask(cleaning);
        Assertions.assertTrue(result);
        Assertions.assertTrue(aide.getAssignedTasks().isEmpty());
    }

    @Test
    public void testCompleteTaskFailure() {
        Aide aide = new Aide("A001", "John Doe");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        Task cooking = new Task("Cooking", 45, 15.0, "Living room cleaning");
        aide.assignTask(cleaning);
        boolean result = aide.completeTask(cooking);
        Assertions.assertFalse(result);
        Assertions.assertEquals(1, aide.getAssignedTasks().size());
    }

    @Test
    public void testToStringWithTasks() {
        Aide aide = new Aide("A001", "John Doe");
        Task cleaning = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        Task cooking = new Task("Cooking", 45, 15.0, "Living room cleaning");
        aide.assignTask(cleaning);
        aide.assignTask(cooking);
        String details = aide.toString();
        Assertions.assertTrue(details.contains("John Doe"));
        Assertions.assertTrue(details.contains("Cleaning"));
        Assertions.assertTrue(details.contains("Cooking"));
        Assertions.assertTrue(details.contains("$25.0"));
        Assertions.assertTrue(details.contains("$15.0"));
    }

    @Test
    public void testToStringWithoutTasks() {
        Aide aide = new Aide("A001", "John Doe");
        String details = aide.toString();
        Assertions.assertTrue(details.contains("John Doe"));
        Assertions.assertTrue(details.contains("No tasks assigned."));
    }
}
