package ua.goit.tasks;

public class TODO {
    int id;
    boolean completed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TODO{" +
                "id=" + id +
                ", completed=" + completed +
                '}';
    }
}
