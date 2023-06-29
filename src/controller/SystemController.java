package controller;

import view.MenuCLI;

public class SystemController {
    private String viewport;

    /* ANCHOR Keep track of state in the controller */

    public SystemController(String viewport) {
        this.viewport = viewport;
    }

    public void start() {
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

    public String getViewport() {
        return viewport;
    }

    public void setViewport(String viewport) {
        this.viewport = viewport;
    }

}
