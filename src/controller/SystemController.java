package controller;

import model.Example;
import util.FileAccessors;
import util.FileWritable;
import view.MenuCLI;

public class SystemController {
    private String viewport;

    /* ANCHOR Keep track of state in the controller */
    private static final String EXAMPLE_ID = "example";
    private Example example = new Example();
    private boolean shutdown = false;

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

    /*
     * Use data files to overwrite state variables
     */
    public void loadData() {
        try {
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

    public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }
}
