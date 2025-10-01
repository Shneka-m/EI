package observer;

public class ConflictObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Observer Alert: " + message);
    }
}
