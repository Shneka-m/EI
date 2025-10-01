package behavioral;

import model.Task;

public class UserObserver implements Observer {
    private final String username;

    public UserObserver(String username) {
        if (username == null || username.trim().isEmpty()) throw new IllegalArgumentException("Username required");
        this.username = username.trim();
    }

    @Override
    public void update(Task task) {
        System.out.println("Notification -> " + username + ": A new task \"" + task.getName() + "\" has been added.");
    }

    @Override
    public String getUsername() { return username; }
}
