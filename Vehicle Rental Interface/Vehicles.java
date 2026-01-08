public class Vehicles {
    private Vehicle[] vehicles;
    private int count = 0;

    private int current = 0;  

    public Vehicles() {
        vehicles = new Vehicle[20]; 
    }

    public void add(Vehicle v) {
        vehicles[count++] = v;
    }

    public Vehicle getVehicle(String vin) throws VINNotFoundException {
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getVIN().equals(vin))
                return vehicles[i];
        }
        throw new VINNotFoundException("VIN not found: " + vin);
    }

    public void reset() {
        current = 0;
    }

    public boolean hasNext() {
        return current < count;
    }

    public Vehicle getNext() {
        return vehicles[current++];
    }
}
