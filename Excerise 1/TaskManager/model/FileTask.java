package model;

public class FileTask implements FileComponent {
    private final String name;
    public FileTask(String name) {
        this.name = name;
    }
    @Override
    public void display() { System.out.println("  - Task: " + name); }
}
