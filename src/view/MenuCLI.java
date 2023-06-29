package view;

import controller.SystemController;

public class MenuCLI {
    private SystemController state;

    public MenuCLI(SystemController state) {
        this.state = state;
    }

    /* ANCHOR Add a CLI */
    public void showMain() {
        System.out.println("The coolest CLI");
    }
}
