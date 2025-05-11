package car.model;

import java.io.Serializable;

public class ExpenseType implements Serializable {
    private int id;
    private String name;
    private String description;

    public ExpenseType(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}