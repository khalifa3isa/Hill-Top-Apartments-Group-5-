// Task.java
// Represents a task assigned to an aide.
public class Task {
    private String type;
    private int duration; // in minutes
    private double cost;

    public Task(String type, int duration, double cost) {
        this.type = type;
        this.duration = duration;
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }
}
