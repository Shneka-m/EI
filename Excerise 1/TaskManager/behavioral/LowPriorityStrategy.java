package behavioral;

import model.Task;

public class LowPriorityStrategy implements PriorityStrategy {
    @Override
    public void applyPriority(Task task) {
        task.setPriority("LOW");
        System.out.println("Priority set to LOW for task: " + task.getName());
    }
}
