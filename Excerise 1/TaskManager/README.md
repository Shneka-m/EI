# Task Manager Application

## Overview
The **Task Manager** application is a console-based project management system that demonstrates **six different software design patterns** (behavioral, creational, structural). It allows users to register for notifications, create tasks, set priorities, and view project structure.

The project emphasizes:
- Modular and well-organized code
- Logging and exception handling
- Defensive programming
- Validations at all levels
- Long-running input handling without hard-coded loops

---

## Design Patterns Demonstrated

### 1. Behavioral Design Patterns
| Use Case | Pattern | Implementation |
|-----------|--------|----------------|
| Task Priority Assignment | Strategy | `PriorityStrategy` interface with `HighPriorityStrategy` and `LowPriorityStrategy` |
| Notifications | Observer-like behavior | `NotificationService` handles notifying specific or all registered users |

### 2. Creational Design Patterns
| Use Case | Pattern | Implementation |
|-----------|--------|----------------|
| Task Creation | Factory | `TaskFactory` creates tasks of type WORK or PERSONAL |
| Task Database | Singleton | `TaskDatabase` ensures a single instance stores all tasks |

### 3. Structural Design Patterns
| Use Case | Pattern | Implementation |
|-----------|--------|----------------|
| Legacy Logger Integration | Adapter | `LoggerAdapter` wraps `OldLogger` to work with `AppLogger` |
| Project Structure Display | Composite | `ProjectFolder` and `FileTask` classes allow hierarchical display of tasks/files |

---

## Features
- Register users for notifications
- Add tasks with type and name
- Apply priority (HIGH/LOW) to tasks
- Show all tasks
- Display project folder structure (Composite pattern)
- List registered users
- Notifications:
  - Specific user or broadcast to all
  - Includes username in notification
- Logging and exception handling (AppLogger)
- Defensive input validation

---

## Project Structure
TaskManager/
├─ behavioral/
│ ├─ HighPriorityStrategy.java
│ ├─ LowPriorityStrategy.java
│ ├─ Observer.java
│ ├─ PriorityStrategy.java
│ ├─ TaskNotifier.java
│ └─ UserObserver.java
├─ config/
│ └─ Config.java
├─ creational/
│ ├─ TaskFactory.java
│ └─ TaskDatabase.java
├─ model/
│ ├─ FileComponent.java
│ ├─ FileTask.java
│ ├─ PersonalTask.java
│ ├─ ProjectFolder.java
│ ├─ Task.java
│ └─ WorkTask.java
├─ registry/
│ └─ UserRegistry.java
├─ service/
│ └─ NotificationService.java
├─ structural/
│ ├─ Logger.java
│ ├─ OldLogger.java
│ └─ LoggerAdapter.java
├─ util/
│ ├─ AppLogger.java
│ ├─ InputUtil.java
│ └─ AppException.java
├─ Main.java
├─ README.md
└─ .gitignore