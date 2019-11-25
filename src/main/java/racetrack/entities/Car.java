package racetrack.entities;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int enginePower;

    public Car(int id, String brand, String model, int enginePower) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.enginePower = enginePower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }
}
