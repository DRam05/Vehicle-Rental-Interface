public class Car extends Vehicle {
    public Car(String desc, int mpg, String vin) {
        super(desc, mpg, vin);
    }

    public String toString() {
        return getDescription() + " (Car) MPG: " + getMpg() + " VIN: " + getVIN();
    }
}