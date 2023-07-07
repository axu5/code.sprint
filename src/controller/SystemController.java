package controller;

import java.io.File;
import java.nio.file.Path;

import model.Example;
import util.FileAccessors;
import util.FileWritable;
import view.MenuCLI;

public class SystemController {
    private String viewport;

    /* ANCHOR Keep track of state in the controller */
    private static final String EXAMPLE_ID = "example";
    private Example example = new Example();

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
        FileWritable.serialize(EXAMPLE_ID, example);
    }

    public void loadData() {
        try {
            /*
             * If an example object data file is found, over write the
             * initial value of this.example
             */
            Object exampleObj = FileAccessors.getObjectById(EXAMPLE_ID);
            if (exampleObj instanceof Example) {
                this.example = (Example) exampleObj;
            }
        } catch (Exception _e) {
        }
    }

    public String getViewport() {
        return viewport;
    }
}
