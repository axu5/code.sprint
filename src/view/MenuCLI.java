package view;

import controller.SystemController;
import model.Board;
import util.InputValidation;
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
                    "Start game",
                    "Exit"
            };

            int option = UserInput.inputTable("Some question", menus);

            switch (option) {
                case 0:
                    this.gameView();
                    break;
                case 1:
                    this.state.setShutdown(true);
                    break;
            }
        }

        // Save the current state to disk
        // state.save();
    }

    public void gameView() {
        Board board = this.state.getBoard();
        System.out.println(board);

        InputValidation validator = new InputValidation() {
            public boolean check(String input) {
                input = input.trim();
                if (input.equalsIgnoreCase("x")) {
                    return true;
                }

                // Should be in the format of #,#
                String[] nums = input.split(",");
                if (nums.length != 2) {
                    return false;
                }
                try {
                    int y = Integer.parseInt(nums[0]);
                    int x = Integer.parseInt(nums[1]);

                    if (y < 0 || y > board.getWidth() - 1 || x < 0 || x > board.getHeight() - 1) {
                        return false;
                    }
                } catch (Exception _e) {
                    return false;
                }

                return true;
            }
        };

        String input = UserInput.input("Enter a location [Row,Column] or E[x]it: ", "Must be in the format #,#",
                validator);

        if (input.equalsIgnoreCase("x")) {
            this.state.setShutdown(true);
            return;
        }

        String[] nums = input.split(",");
        int x = Integer.parseInt(nums[0]);
        int y = Integer.parseInt(nums[1]);

        String[] options = {
                "F",
                "R",
        };

        String todo = UserInput.useOptionsIgnoreCase("What would you like to do? (F=Flag, R=Reveal) ", options);

        boolean gameOver = false;
        switch (todo) {
            case "F":
                board.flag(y, x);
                break;
            case "R":
                gameOver = board.reveal(y, x);
                break;
        }

        if (gameOver) {
            this.gameOverView();
            return;
        }

        this.gameView();
    }

    public void gameOverView() {
        Board board = this.state.getBoard();

        System.out.println(" ------------------------------");
        System.out.println(" |     G A M E    O V E R     |");
        System.out.println(" |       YOU HIT A BOMB       |");
        System.out.println(" ------------------------------");

        System.out.println();

        System.out.println(board.gameEndBoard());

        // Reveal full board

        String todo = UserInput.inputOptional(" ...[R]estart or press Enter to exit: ");
        if (todo.equalsIgnoreCase("r")) {
            board.reset();
            return;
        }

        this.state.setShutdown(true);
    }
}
