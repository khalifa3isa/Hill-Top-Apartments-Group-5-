//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scheduler {
    private Map<Aide, List<Task>> aideSchedules = new HashMap();
    private Map<Client, List<Task>> clientSchedules = new HashMap();

    public Scheduler() {
    }

    public void scheduleTask(Task task, Aide aide, Client client) {
        aide.assignTask(task);
        ((List)this.aideSchedules.computeIfAbsent(aide, (k) -> {
            return new ArrayList();
        })).add(task);
        client.assignTask(task);
        ((List)this.clientSchedules.computeIfAbsent(client, (k) -> {
            return new ArrayList();
        })).add(task);
        PrintStream var10000 = System.out;
        String var10001 = task.getType();
        var10000.println("Task scheduled: " + var10001 + " for Aide: " + aide.toString() + " and Client: " + client.toString());
    }

    public List<Task> getTasksForAide(Aide aide) {
        return (List)this.aideSchedules.getOrDefault(aide, new ArrayList());
    }

    public List<Task> getTasksForClient(Client client) {
        return (List)this.clientSchedules.getOrDefault(client, new ArrayList());
    }

    public String toString() {
        StringBuilder scheduleDetails = new StringBuilder();
        scheduleDetails.append("Aide Schedules:\n");
        this.aideSchedules.forEach((aide, tasks) -> {
            scheduleDetails.append(aide.toString()).append(":\n");
            tasks.forEach((task) -> {
                scheduleDetails.append("- ").append(task.toString()).append("\n");
            });
        });
        scheduleDetails.append("Client Schedules:\n");
        this.clientSchedules.forEach((client, tasks) -> {
            scheduleDetails.append(client.toString()).append(":\n");
            tasks.forEach((task) -> {
                scheduleDetails.append("- ").append(task.toString()).append("\n");
            });
        });
        return scheduleDetails.toString();
    }
}
