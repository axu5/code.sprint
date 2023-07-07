package model;

import java.io.Serializable;

public class Example implements Serializable {
    private String message = "My message";

    public Example() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
