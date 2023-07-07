package view;

import controller.SystemController;
import util.UserInput;

public class MenuCLI {
    private SystemController state;

    public MenuCLI(SystemController state) {
        this.state = state;
    }

    /* ANCHOR Add a CLI */
    public void showMain() {
        System.out.println("The coolest CLI");

        /**
         * Wait until the program is called to shut down through the state variable
         * "shutdown." This ensures the program can be shutdown from anywhere where
         * there is access to the program state.
         */
        while (!this.state.isShutdown()) {
            String[] menus = {
                    "First view",
                    "Second view",
                    "Exit"
            };

            int option = UserInput.inputTable("Some question", menus);

            switch (option) {
                case 0:
                    this.view1();
                    break;
                case 1:
                    this.view2();
                    break;
                case 3:
                    this.state.setShutdown(true);
                    break;
            }
        }

        // Save the current state to disk
        state.save();
    }

    public void view1() {
        System.out.println("View 1");
    }

    public void view2() {
        System.out.println("View 2");
    }
}
