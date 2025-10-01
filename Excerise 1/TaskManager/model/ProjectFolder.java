package model;

import java.util.ArrayList;
import java.util.List;

public class ProjectFolder implements FileComponent {
    private final String name;
    private final List<FileComponent> components = new ArrayList<>();

    public ProjectFolder(String name) {
        this.name = (name == null ? "Project" : name);
    }

    public void add(FileComponent c) {
        if (c != null) components.add(c);
    }

    @Override
    public void display() {
        System.out.println("Folder: " + name);
        for (FileComponent c : components) c.display();
    }
}
