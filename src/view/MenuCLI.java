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

        mainLoop: while (true) {
            String[] menus = {
                    Views.View1.getName(),
                    Views.View2.getName(),
                    Views.Exit.getName(),
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
                    break mainLoop;
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
