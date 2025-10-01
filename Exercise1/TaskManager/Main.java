import config.Config;
import util.AppLogger;
import util.InputUtil;
import util.AppException;
import creational.TaskDatabase;
import creational.TaskFactory;
import behavioral.PriorityStrategy;
import behavioral.HighPriorityStrategy;
import behavioral.LowPriorityStrategy;
import model.Task;
import model.ProjectFolder;
import model.FileTask;
import service.NotificationService;

public class Main {

    public static void main(String[] args) {
        AppLogger.info("Task Manager starting...");
        final TaskDatabase db = TaskDatabase.getInstance();

        // Sample pre-registered users
        NotificationService.registerUser("Alice");
        NotificationService.registerUser("Bob");

        // Shutdown hook for graceful stop
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            AppLogger.info("Shutting down Task Manager gracefully.");
            Config.RUNNING.set(false);
        }));

        while (Config.RUNNING.get()) {
            try {
                System.out.println("\n===== TASK MANAGER =====");
                System.out.println("1. Register user for notifications");
                System.out.println("2. Add task");
                System.out.println("3. Apply priority to a task");
                System.out.println("4. Show all tasks");
                System.out.println("5. Show project structure");
                System.out.println("6. List registered users");
                System.out.println("7. Exit");

                int choice = InputUtil.readInt("Enter choice: ");

                switch (choice) {
                    case 1 -> {
                        String username = InputUtil.readNonEmpty("Enter username to register: ");
                        NotificationService.registerUser(username);
                    }
                    case 2 -> {
                        String type = InputUtil.readNonEmpty("Enter Task Type (WORK/PERSONAL): ");
                        String name = InputUtil.readNonEmpty("Enter Task Name: ");
                        Task task = TaskFactory.createTask(type, name);
                        db.addTask(task);

                        System.out.print("Notify specific user? Enter username or press Enter to notify all: ");
                        String username = InputUtil.readLineOptional();
                        if (username.isEmpty()) {
                            NotificationService.notifyAllUsers("New task added: " + name);
                        } else {
                            NotificationService.notifyUser(username, "New task assigned: " + name);
                        }

                        AppLogger.info("Task added: " + name + " of type " + type);
                    }
                    case 3 -> {
                        String tName = InputUtil.readNonEmpty("Enter Task Name to set Priority: ");
                        Task found = db.getTasks().stream().filter(t -> t.getName().equals(tName)).findFirst().orElse(null);
                        if (found == null) {
                            System.out.println("Task not found: " + tName);
                            AppLogger.warn("Attempted to set priority for missing task: " + tName);
                            break;
                        }
                        String p = InputUtil.readNonEmpty("Priority (HIGH/LOW): ");
                        PriorityStrategy strategy = p.equalsIgnoreCase("HIGH") ? new HighPriorityStrategy() : new LowPriorityStrategy();
                        strategy.applyPriority(found);
                        AppLogger.info("Priority applied to task: " + tName + " -> " + found.getPriority());
                    }
                    case 4 -> {
                        System.out.println("All Tasks:");
                        db.getTasks().forEach(t -> System.out.println(" - " + t.getName() + " [" + t.getPriority() + "]"));
                    }
                    case 5 -> {
                        ProjectFolder root = new ProjectFolder("MyProject");
                        db.getTasks().forEach(t -> root.add(new FileTask(t.getName())));
                        root.display();
                    }
                    case 6 -> NotificationService.listUsers();
                    case 7 -> Config.RUNNING.set(false);
                    default -> System.out.println("Invalid choice.");
                }
            } catch (AppException ae) {
                AppLogger.warn("User input error: " + ae.getMessage());
            } catch (Exception e) {
                AppLogger.error("Unexpected error in main loop", e);
            }
        }

        AppLogger.info("Task Manager stopped.");
    }
}
