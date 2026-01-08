public class Minivan extends Vehicle {
    private int cargo;

    public Minivan(String desc, int mpg, int cargo, String vin) {
        super(desc, mpg, vin);
        this.cargo = cargo;
    }

    public String toString() {
        return getDescription() + " (Minivan) MPG: " + getMpg() + 
               " Cargo: " + cargo + " cu. ft. VIN: " + getVIN();
    }
}
