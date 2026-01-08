public abstract class Vehicle {
    private String description;
    private int mpg;
    private String vin;

    private ReservationDetails resv;   
    private VehicleRates quotedRates;    

    public Vehicle(String description, int mpg, String vin) {
        this.description = description;
        this.mpg = mpg;
        this.vin = vin;
        this.resv = null;
        this.quotedRates = null;
    }

    public String getDescription() { return description; }
    public int getMpg() { return mpg; }
    public String getVIN() { return vin; }

    public ReservationDetails getReservation() {
        return resv;
    }

    public VehicleRates getQuotedRates() {
        return quotedRates;
    }

    public boolean isReserved() {
        return resv != null;
    }

    public void setReservation(ReservationDetails r) throws ReservedVehicleException {
        if (resv != null)
            throw new ReservedVehicleException("Vehicle already reserved: " + vin);
        this.resv = r;
    }

    public void setQuotedRates(VehicleRates r) {
        this.quotedRates = new VehicleRates(r); 
    }

    public void cancelReservation() throws UnreservedVehicleException {
        if (resv == null)
            throw new UnreservedVehicleException("Vehicle not reserved: " + vin);
        resv = null;
        quotedRates = null;
    }

    public abstract String toString();
}
