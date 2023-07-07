package model;

import java.io.Serializable;

public class Cell implements Serializable {
    private boolean hidden = true;
    private boolean bomb = false;
    private boolean flagged = false;
    private boolean hit = false;
    private int neighbors = 0;

    public Cell() {
    }

    public String toString() {
        // Flagged logic
        if (this.hidden && !this.flagged) {
            return " ";
        } else if (this.hidden && this.flagged) {
            return "F";
        }

        // Bomb logic
        if (this.bomb && this.hit) {
            return "#";
        } else if (this.bomb && !this.hit) {
            return "B";
        }

        return "" + neighbors;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public void toggleFlagged() {
        this.flagged = !this.flagged;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public int getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }
}
