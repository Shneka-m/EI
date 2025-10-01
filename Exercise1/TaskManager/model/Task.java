package model;

public abstract class Task {
    private final String name;
    private volatile String priority = "NORMAL";

    public Task(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Task name required");
        this.name = name.trim();
    }

    public String getName() { return name; }

    public String getPriority() { return priority; }

    public void setPriority(String priority) {
        if (priority == null) throw new IllegalArgumentException("Priority cannot be null");
        this.priority = priority;
    }
}
