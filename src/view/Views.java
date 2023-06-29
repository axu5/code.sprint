package view;

public enum Views {
    CreateTodo("Create a Todo"),
    ViewTodo("View a Todo"),
    CompleteTodo("Complete a Todo"),
    Exit("Exit");

    private String name;

    private Views(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
