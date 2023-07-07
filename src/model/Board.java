package model;

import java.io.Serializable;

public class Board implements Serializable {
    private int height = 10;
    private int width = 10;
    private Cell[][] table;

    public Board() {
        this.reset();
    }

    public void reset() {
        this.table = new Cell[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                this.table[i][j] = new Cell();
            }
        }

        // Select 10 random bomb locations
        int bombsRemaining = 10;
        while (bombsRemaining > 0) {
            int y = (int) (Math.random() * table.length);
            int x = (int) (Math.random() * table[y].length);

            if (this.table[y][x].isBomb()) {
                continue;
            }

            // Add to neighboring count
            for (int i = Math.max(y - 1, 0); i <= Math.min(y + 1, height - 1); ++i) {
                for (int j = Math.max(x - 1, 0); j <= Math.min(x + 1, width - 1); ++j) {
                    int n = this.table[i][j].getNeighbors();
                    this.table[i][j].setNeighbors(n + 1);
                }
            }

            this.table[y][x].setBomb(true);
            bombsRemaining--;
        }
    }

    public void flag(int x, int y) {
        boolean flag = this.table[x][y].isFlagged();
        this.table[x][y].setFlagged(!flag);
    }

    /**
     * @return Returns a boolean indicating whether or not the game has ended
     */
    public boolean reveal(int x, int y, boolean player) {
        if (this.table[y][x].isBomb() && player) {
            this.table[y][x].setHit(true);
            return true;
        }

        this.table[y][x].setHidden(false);

        if (this.table[y][x].getNeighbors() > 0) {
            return false;
        }

        for (int i = Math.max(x - 1, 0); i < Math.min(x + 2, width); ++i) {
            for (int j = Math.max(y - 1, 0); j < Math.min(y + 2, height); ++j) {
                if (!this.table[j][i].isHidden() || (i == x && j == y)) {
                    continue;
                }
                this.reveal(i, j, false);
            }
        }

        return false;
    }

    public boolean reveal(int x, int y) {
        return this.reveal(x, y, true);
    }

    public String gameEndBoard() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                this.table[i][j].setHidden(false);
            }
        }

        String out = "   ";

        for (int j = 0; j < table[0].length; ++j) {
            out += "  " + j + " ";
        }

        out += "\n   " + "----".repeat(width) + "\n";

        for (int i = 0; i < height; ++i) {
            // TODO rightpad
            out += i + "  ";
            for (int j = 0; j < width; ++j) {
                out += "| " + this.table[j][i] + " ";
            }
            out += "|\n";
        }

        out += "   " + "----".repeat(width);

        return out;
    }

    public String toString() {
        String out = "   ";

        for (int j = 0; j < table[0].length; ++j) {
            out += "  " + j + " ";
        }

        out += "\n   " + "----".repeat(width) + "\n";

        for (int i = 0; i < table.length; ++i) {
            // TODO rightpad
            out += i + "  ";
            for (int j = 0; j < table[i].length; ++j) {
                out += "| " + this.table[j][i] + " ";
            }
            out += "|\n";
        }

        out += "   " + "----".repeat(width);

        return out;
    }

    public Cell getCell(int x, int y) {
        return table[y][x];
    }

    public Cell[][] getTable() {
        return table;
    }

    public void setTable(Cell[][] table) {
        this.table = table;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
