package car.model;

import java.io.Serializable;

public class Car implements Serializable {
    private int id;
    private String model;
    private int year;
    private String licensePlate;

    public Car(int id, String model, int year, String licensePlate) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String toString() {
        return model + " (" + year + ") [" + licensePlate + "]";
    }
}