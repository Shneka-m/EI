package registry;

import behavioral.Observer;
import behavioral.UserObserver;
import behavioral.TaskNotifier;

import java.util.List;

public class UserRegistry {
    private final TaskNotifier notifier;

    public UserRegistry(TaskNotifier notifier) {
        this.notifier = notifier;
    }

    public void registerUser(String username) {
        UserObserver u = new UserObserver(username);
        notifier.register(u);
    }

    public List<Observer> listUsers() {
        return notifier.listObservers();
    }
}
