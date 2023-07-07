package controller;

import model.Board;
import util.FileAccessors;
import util.FileWritable;
import view.MenuCLI;

public class SystemController {
    private String viewport;

    /* ANCHOR Keep track of state in the controller */
    private static final String BOARD_ID = "game";
    private Board board = new Board();
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
        FileWritable.serialize(BOARD_ID, board);
    }

    /*
     * Use data files to overwrite state variables
     */
    public void loadData() {
        try {
            Object exampleObj = FileAccessors.getObjectById(BOARD_ID);
            if (exampleObj instanceof Board) {
                this.board = (Board) exampleObj;
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

    public void setViewport(String viewport) {
        this.viewport = viewport;
    }

    public static String getBoardId() {
        return BOARD_ID;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
