package controller;

import java.io.File;
import java.util.ArrayList;

import model.Todo;
import util.FileAccessors;
import util.FileWritable;
import view.MenuCLI;

public class SystemController {
    private String viewport;
    private static final String TODOS_ID = "todos";
    private ArrayList<Todo> todos = new ArrayList<Todo>();

    /* ANCHOR Keep track of state in the controller */

    public SystemController(String viewport) {
        this.viewport = viewport;
    }

    public void start() {
        this.loadData();

        switch (this.viewport) {
            case "CLI":
                MenuCLI menuCLI = new MenuCLI(this);
                menuCLI.showMain();
                break;
            default:
                System.out.println("Incorrect viewport");
                break;
        }
    }

    public void save() {
        FileWritable.serialize(TODOS_ID, todos);
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        String dataDirectory = FileWritable.getDataDirectory();
        File[] files = FileAccessors.getFiles(dataDirectory);
        if (files.length > 0) {
            System.out.println("Loading data...");
        }

        for (File file : files) {
            switch (file.getName()) {
                case (TODOS_ID + ".data"):
                    // Warnings are suppressed so that this cast can be done
                    Object obj = FileAccessors.parse(file);
                    if (obj instanceof ArrayList) {
                        this.todos = (ArrayList<Todo>) obj;
                    }
                    break;
            }
        }
    }

    public void addTodo(Todo todo) {
        this.todos.add(todo);
    }

    public String getViewport() {
        return viewport;
    }

    public void setViewport(String viewport) {
        this.viewport = viewport;
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }
}
