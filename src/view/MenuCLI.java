package view;

import controller.SystemController;
import model.Todo;
import util.UserInput;

public class MenuCLI {
    private SystemController state;

    public MenuCLI(SystemController state) {
        this.state = state;
    }

    /* ANCHOR Add a CLI */
    public void showMain() {
        mainLoop: while (true) {
            // Determine which menu the user wants to go into
            String[] menus = {
                    Views.CreateTodo.getName(),
                    Views.ViewTodo.getName(),
                    Views.CompleteTodo.getName(),
                    Views.Exit.getName(),
            };
            int option = UserInput.inputTable("What would you like to do? (pun intended)", menus);

            switch (option) {
                case 0:
                    this.createTodo();
                    break;
                case 1:
                    this.viewTodos();
                    break;
                case 2:
                    this.editTodo();
                    break;
                case 3:
                    break mainLoop;
                default:
                    // This code should be unreachable
                    System.out.println("THIS CODE SHOULD BE UNREACHABLE UH OH");
            }

            System.out.println("\n- - - - - - - - - -\n");
        }

        System.out.println("Saving...");
        state.save();
    }

    public void createTodo() {
        String name = UserInput.input("What would you like to call your todo? ");
        Todo todo = new Todo(name);
        this.state.addTodo(todo);
    }

    public void viewTodos() {
        System.out.println("Here is a list of all your todos");
        Todo[] todos = this.state.getTodos().toArray(Todo[]::new);

        for (int i = 0; i < todos.length; ++i) {
            Todo todo = todos[i];
            System.out.printf("%d. [%c] %s\n", i + 1, todo.isDone() ? 'x' : ' ', todo.getTodo());
        }
    }

    public void editTodo() {
        Todo[] todos = this.state.getTodos().toArray(Todo[]::new);
        String[] todoNames = new String[todos.length];

        for (int i = 0; i < todos.length; ++i) {
            Todo todo = todos[i];
            todoNames[i] = todo.getTodo();
        }

        int selectedTodoIdx = UserInput.inputTable("Which TODO would you like to edit?", todoNames);

        // Toggle if todo is done or not
        todos[selectedTodoIdx].toggleDone();
    }
}
