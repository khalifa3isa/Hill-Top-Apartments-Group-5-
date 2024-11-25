

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SchedulerTest {
    public SchedulerTest() {
    }

    @Test
    public void testScheduleTask() {
        Scheduler scheduler = new Scheduler();
        Aide aide = new Aide("A001", "John Doe");
        Client client = new Client("C001", "Jane Smith", "jane.smith@example.com");
        Task task = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        scheduler.scheduleTask(task, aide, client);
        List<Task> aideTasks = scheduler.getTasksForAide(aide);
        List<Task> clientTasks = scheduler.getTasksForClient(client);
        Assertions.assertEquals(1, aideTasks.size());
        Assertions.assertEquals("Cleaning", ((Task)aideTasks.get(0)).getType());
        Assertions.assertEquals(1, clientTasks.size());
        Assertions.assertEquals("Cleaning", ((Task)clientTasks.get(0)).getType());
    }

    @Test
    public void testGetTasksForAideNoTasks() {
        Scheduler scheduler = new Scheduler();
        Aide aide = new Aide("A001", "John Doe");
        List<Task> tasks = scheduler.getTasksForAide(aide);
        Assertions.assertTrue(tasks.isEmpty());
    }

    @Test
    public void testGetTasksForClientNoTasks() {
        Scheduler scheduler = new Scheduler();
        Client client = new Client("C001", "Jane Smith", "jane.smith@example.com");
        List<Task> tasks = scheduler.getTasksForClient(client);
        Assertions.assertTrue(tasks.isEmpty());
    }

    @Test
    public void testToStringWithSchedules() {
        Scheduler scheduler = new Scheduler();
        Aide aide = new Aide("A001", "John Doe");
        Client client = new Client("C001", "Jane Smith", "jane.smith@example.com");
        Task task1 = new Task("Cleaning", 60, 25.0, "Living room cleaning");
        Task task2 = new Task("Laundry", 45, 15.0, "Living room cleaning");
        scheduler.scheduleTask(task1, aide, client);
        scheduler.scheduleTask(task2, aide, client);
        String scheduleDetails = scheduler.toString();
        Assertions.assertTrue(scheduleDetails.contains("John Doe"));
        Assertions.assertTrue(scheduleDetails.contains("Jane Smith"));
        Assertions.assertTrue(scheduleDetails.contains("Cleaning"));
        Assertions.assertTrue(scheduleDetails.contains("Laundry"));
    }

    @Test
    public void testToStringNoSchedules() {
        Scheduler scheduler = new Scheduler();
        String scheduleDetails = scheduler.toString();
        Assertions.assertTrue(scheduleDetails.contains("Aide Schedules:"));
        Assertions.assertTrue(scheduleDetails.contains("Client Schedules:"));
    }
}
