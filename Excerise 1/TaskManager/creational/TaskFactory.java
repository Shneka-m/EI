package creational;

import model.Task;
import model.WorkTask;
import model.PersonalTask;

public class TaskFactory {
    public static Task createTask(String type, String name) {
        if (type == null) throw new IllegalArgumentException("Type is required");
        switch (type.trim().toUpperCase()) {
            case "WORK": return new WorkTask(name);
            case "PERSONAL": return new PersonalTask(name);
            default: throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }
}
