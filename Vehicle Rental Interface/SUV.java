public class SUV extends Vehicle {
    private int cargo;

    public SUV(String desc, int mpg, int cargo, String vin) {
        super(desc, mpg, vin);
        this.cargo = cargo;
    }

    public String toString() {
        return getDescription() + " (SUV) MPG: " + getMpg() + 
               " Cargo: " + cargo + " cu. ft. VIN: " + getVIN();
    }
}
