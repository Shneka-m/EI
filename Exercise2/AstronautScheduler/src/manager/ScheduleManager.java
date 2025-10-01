package manager;

import factory.TaskFactory;
import model.PriorityLevel;
import model.Task;
import observer.ConflictObserver;
import observer.Observer;
import exception.TaskException;
import util.LoggerUtil;

import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<Observer> observers;
    private static final Logger logger = LoggerUtil.getLogger(ScheduleManager.class.getName());

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
        observers.add(new ConflictObserver());
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(String desc, String start, String end, PriorityLevel priority) throws TaskException {
        Task newTask = TaskFactory.createTask(desc, start, end, priority);

        for (Task t : tasks) {
            boolean overlap = !(newTask.getEndTime().isBefore(t.getStartTime()) || newTask.getStartTime().isAfter(t.getEndTime()));
            if (overlap) {
                notifyObservers("Task conflicts with existing task \"" + t.getDescription() + "\".");
                throw new TaskException("Task conflicts with existing task \"" + t.getDescription() + "\".");
            }
        }

        tasks.add(newTask);
        logger.info("Task added: " + newTask);
        System.out.println("Task added successfully. No conflicts.");
    }

    public void removeTask(String desc) throws TaskException {
        Optional<Task> taskOpt = tasks.stream().filter(t -> t.getDescription().equalsIgnoreCase(desc)).findFirst();

        if (taskOpt.isPresent()) {
            tasks.remove(taskOpt.get());
            logger.info("Task removed: " + desc);
            System.out.println("Task removed successfully.");
        } else {
            throw new TaskException("Task not found.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }

        tasks.stream()
                .sorted(Comparator.comparing(Task::getStartTime))
                .forEach(System.out::println);
    }

    public void editTask(String desc, String newDesc, String start, String end, String priority) throws TaskException {
        Optional<Task> taskOpt = tasks.stream().filter(t -> t.getDescription().equalsIgnoreCase(desc)).findFirst();

        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();

            if (!newDesc.isBlank()) task.setDescription(newDesc);
            if (!start.isBlank()) task.setStartTime(LocalTime.parse(start));
            if (!end.isBlank()) task.setEndTime(LocalTime.parse(end));
            if (!priority.isBlank()) task.setPriority(PriorityLevel.valueOf(priority));

            logger.info("Task edited: " + task);
            System.out.println("Task updated successfully.");
        } else {
            throw new TaskException("Task not found.");
        }
    }

    public void markTaskCompleted(String desc) throws TaskException {
        Optional<Task> taskOpt = tasks.stream().filter(t -> t.getDescription().equalsIgnoreCase(desc)).findFirst();

        if (taskOpt.isPresent()) {
            taskOpt.get().markCompleted();
            logger.info("Task marked completed: " + desc);
            System.out.println("Task marked as completed.");
        } else {
            throw new TaskException("Task not found.");
        }
    }

    public void viewTasksByPriority(PriorityLevel priority) {
        tasks.stream()
                .filter(t -> t.getPriority() == priority)
                .sorted(Comparator.comparing(Task::getStartTime))
                .forEach(System.out::println);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
