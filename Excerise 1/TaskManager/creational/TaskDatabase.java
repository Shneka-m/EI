package creational;

import model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskDatabase {
    private static volatile TaskDatabase instance;
    private final List<Task> tasks;

    private TaskDatabase() {
        tasks = Collections.synchronizedList(new ArrayList<>());
    }

    public static TaskDatabase getInstance() {
        if (instance == null) {
            synchronized (TaskDatabase.class) {
                if (instance == null) instance = new TaskDatabase();
            }
        }
        return instance;
    }

    public void addTask(Task t) {
        if (t == null) throw new IllegalArgumentException("Task cannot be null");
        tasks.add(t);
    }

    public List<Task> getTasks() {
        synchronized (tasks) {
            return new ArrayList<>(tasks); 
        }
    }
}
