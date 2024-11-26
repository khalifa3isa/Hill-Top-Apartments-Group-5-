import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Aide {
    private String aideId;
    private String name;
    private List<Task> assignedTasks;

    public Aide(String aideId, String name) {
        this.aideId = aideId;
        this.name = name;
        this.assignedTasks = new ArrayList();
    }

    public void assignTask(Task task) {
        this.assignedTasks.add(task);
        String var10001 = this.name;
        System.out.println("Task assigned to " + var10001 + ": " + task.getType());
    }

    public boolean completeTask(Task task) {
        String var10001;
        if (this.assignedTasks.contains(task)) {
            this.assignedTasks.remove(task);
            var10001 = this.name;
            System.out.println("Task completed by " + var10001 + ": " + task.getType());
            return true;
        } else {
            var10001 = this.name;
            System.out.println("Task not found for aide " + var10001 + ": " + task.getType());
            return false;
        }
    }

    public List<Task> getAssignedTasks() {
        return this.assignedTasks;
    }

    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Aide ID: ").append(this.aideId).append("\n");
        details.append("Name: ").append(this.name).append("\n");
        details.append("Assigned Tasks:\n");
        if (this.assignedTasks.isEmpty()) {
            details.append("No tasks assigned.\n");
        } else {
            Iterator var2 = this.assignedTasks.iterator();

            while(var2.hasNext()) {
                Task task = (Task)var2.next();
                details.append("- ").append(task.getType()).append(" (").append(task.getDuration()).append(" mins, $").append(task.getCost()).append(")\n");
            }
        }

        return details.toString();
    }
}
