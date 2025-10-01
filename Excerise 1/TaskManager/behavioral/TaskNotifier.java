package behavioral;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskNotifier {
    private final List<Observer> observers = new ArrayList<>();

    public synchronized void register(Observer o) {
        if (o != null) observers.add(o);
    }

    public synchronized void unregister(Observer o) {
        observers.remove(o);
    }

    public synchronized Optional<Observer> findByUsername(String username) {
        return observers.stream().filter(ob -> ob.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    public synchronized void notifyObservers(Task task) {
        for (Observer o : new ArrayList<>(observers)) { // snapshot
            try { o.update(task); } catch (Exception e) { /* don't let one observer break others */ }
        }
    }

    public synchronized void notifyUser(Task task, String username) {
        if (username == null || username.trim().isEmpty()) {
            notifyObservers(task);
            return;
        }
        Optional<Observer> found = findByUsername(username.trim());
        if (found.isPresent()) {
            try { found.get().update(task); } catch (Exception e) { /* ignore */ }
        } else {
            // fallback: notify all if specific username not found
            notifyObservers(task);
        }
    }

    public synchronized List<Observer> listObservers() {
        return new ArrayList<>(observers);
    }
}
