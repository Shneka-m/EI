import manager.ScheduleManager;
import model.PriorityLevel;
import util.LoggerUtil;
import java.util.Scanner;
import java.util.logging.Logger;
public class Main {
	private static final Logger logger = LoggerUtil.getLogger(Main.class.getName());
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ScheduleManager manager = ScheduleManager.getInstance();
		boolean exit = false;
		logger.info("Astronaut Daily Schedule Organizer Started.");
		while (!exit) {
			System.out.println("\n===== Astronaut Daily Schedule Organizer =====");
			System.out.println("1. Add Task");
			System.out.println("2. Remove Task");
			System.out.println("3. View All Tasks");
			System.out.println("4. Edit Task");
			System.out.println("5. Mark Task as Completed");
			System.out.println("6. View Tasks by Priority");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");
			try {
				int choice = Integer.parseInt(scanner.nextLine().trim());
				switch (choice) {
				case 1 -> { System.out.print("Enter description: "); String desc = scanner.nextLine(); System.out.print("Enter start time (HH:mm): "); String start = scanner.nextLine(); System.out.print("Enter end time (HH:mm): "); String end = scanner.nextLine(); System.out.print("Enter priority (HIGH, MEDIUM, LOW): "); String priority = scanner.nextLine().toUpperCase(); manager.addTask(desc, start, end, PriorityLevel.valueOf(priority)); } case 2 -> { System.out.print("Enter task description to remove: "); String desc = scanner.nextLine(); manager.removeTask(desc); } case 3 -> manager.viewTasks();
				case 4 -> { System.out.print("Enter task description to edit: "); String desc = scanner.nextLine(); System.out.print("Enter new description (leave blank to keep same): "); String newDesc = scanner.nextLine(); System.out.print("Enter new start time (HH:mm or blank): "); String start = scanner.nextLine(); System.out.print("Enter new end time (HH:mm or blank): "); String end = scanner.nextLine(); System.out.print("Enter new priority (HIGH, MEDIUM, LOW or blank): "); String priority = scanner.nextLine().toUpperCase(); manager.editTask(desc, newDesc, start, end, priority); } case 5 -> { System.out.print("Enter task description to mark as completed: "); String desc = scanner.nextLine(); manager.markTaskCompleted(desc); } case 6 -> { System.out.print("Enter priority to filter (HIGH, MEDIUM, LOW): "); String priority = scanner.nextLine().toUpperCase(); manager.viewTasksByPriority(PriorityLevel.valueOf(priority)); } case 7 -> { logger.info("Exiting application."); exit = true; } default -> System.out.println("Invalid choice.");
				}
			}
			catch (Exception e) {
				logger.severe("Error: " + e.getMessage());
				System.out.println("Error: " + e.getMessage());
			}
		}
		scanner.close();
	}
}
