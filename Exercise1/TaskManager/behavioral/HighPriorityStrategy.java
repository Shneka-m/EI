package behavioral;

import model.Task;

public class HighPriorityStrategy implements PriorityStrategy {
    @Override
    public void applyPriority(Task task) {
        task.setPriority("HIGH");
        System.out.println("Priority set to HIGH for task: " + task.getName());
    }
}
