package view;

public enum Views {
    /* ANCHOR Create views */
    View1("First view"),
    View2("Second view"),
    Exit("Exit");

    private String name;

    private Views(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
