package model;

import java.io.Serializable;

public class Todo implements Serializable {
    private String todo;
    private boolean done;

    public Todo(String todo) {
        this.todo = todo;
        this.done = false;
    }

    public void toggleDone() {
        this.done = !this.done;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
