package car.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense implements Serializable {
    private int id;
    private int carId;
    private int typeId;
    private LocalDate date;
    private BigDecimal amount;
    private String description;

    public Expense(int id, int carId, int typeId, LocalDate date, BigDecimal amount, String description) {
        this.id = id;
        this.carId = carId;
        this.typeId = typeId;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getCarId() {
        return carId;
    }

    public int getTypeId() {
        return typeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return date + " | " + amount + " | " + description;
    }
}
