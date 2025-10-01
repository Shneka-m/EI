package behavioral;

import model.Task;

public interface Observer {
    void update(Task task);
    String getUsername();
}
