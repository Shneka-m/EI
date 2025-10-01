package factory;

import model.PriorityLevel;
import model.Task;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import exception.TaskException;

public class TaskFactory {

    public static Task createTask(String description, String start, String end, PriorityLevel priority) throws TaskException {
        try {
            LocalTime startTime = LocalTime.parse(start);
            LocalTime endTime = LocalTime.parse(end);

            if (endTime.isBefore(startTime)) {
                throw new TaskException("End time must be after start time.");
            }

            return new Task(description, startTime, endTime, priority);
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid time format. Use HH:mm.");
        }
    }
}
