# Astronaut Daily Schedule Organizer

A **console-based Java application** to help astronauts organize their daily tasks. The application supports adding, removing, editing, and viewing tasks, along with task prioritization and completion tracking. This project demonstrates **OOP design, design patterns, exception handling, logging, and SOLID principles**.

---

## **Features**

### Mandatory:
- Add a task with description, start time, end time, and priority level.
- Remove an existing task.
- View all tasks sorted by start time.
- Validate that new tasks do not overlap with existing tasks.
- Error messages for invalid operations.

### Optional:
- Edit an existing task.
- Mark tasks as completed.
- View tasks filtered by priority.

---

## **Design Patterns Used**
- **Singleton Pattern**: `ScheduleManager` ensures a single instance manages tasks.
- **Factory Pattern**: `TaskFactory` creates task objects.
- **Observer Pattern**: `ConflictObserver` alerts for task conflicts.

---

## **Technologies**
- Java 11+
- Logging with `java.util.logging`
- Observer pattern for conflict notifications
- Custom exception handling (`TaskException`)

---

## **Project Structure**
AstronautScheduler/
├── src/
│ ├── Main.java
│ ├── model/
│ │ ├── Task.java
│ │ └── PriorityLevel.java
│ ├── factory/
│ │ └── TaskFactory.java
│ ├── manager/
│ │ └── ScheduleManager.java
│ ├── observer/
│ │ ├── Observer.java
│ │ └── ConflictObserver.java
│ ├── util/
│ │ └── LoggerUtil.java
│ ├── exception/
│ │ └── TaskException.java
├── README.md
└── .gitignore