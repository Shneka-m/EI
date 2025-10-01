package model;

import java.time.LocalTime;

public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private PriorityLevel priority;
    private boolean completed;

    public Task(String description, LocalTime startTime, LocalTime endTime, PriorityLevel priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public PriorityLevel getPriority() {
        return priority;
    }
    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime + ": " + description +
                " [" + priority + "]" + (completed ? " (Completed)" : "");
    }
}
