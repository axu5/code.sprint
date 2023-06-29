package controller;

import java.io.File;

import util.FileAccessors;
import util.FileWritable;
import view.MenuCLI;

public class SystemController {
    private String viewport;

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

    /**
     * Save important data
     */
    public void save() {
    }

    // @SuppressWarnings("unchecked")
    public void loadData() {
        String dataDirectory = FileWritable.getDataDirectory();
        File[] files = FileAccessors.getFiles(dataDirectory);
        if (files.length > 0) {
            System.out.println("Loading data...");
        }

        // for (File file : files) {
        // switch (file.getName()) {
        // case "example_id":
        // // Warnings are suppressed so that this cast can be done
        // Object obj = FileAccessors.parse(file);
        // if (obj instanceof ArrayList) {
        // this.todos = (ArrayList<Todo>) obj;
        // }
        // break;
        // }
        // }
    }

    public String getViewport() {
        return viewport;
    }
}
