package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SchedulerTest {
    @Test
    public void testScheduleTask() {
        Task task = new Task("Dressing Assistance", 30, 15.0);
        Scheduler scheduler = new Scheduler();
        assertDoesNotThrow(() -> scheduler.scheduleTask(task, "Aide1", "Client1"));
    }
}

