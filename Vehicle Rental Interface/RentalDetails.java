public class RentalDetails {
    private String vehicle_type;
    private TimePeriod rental_period;
    private int num_miles_driven;
    private boolean insurance_selected;

    public RentalDetails(String type, TimePeriod period, int miles, boolean insurance) {
        this.vehicle_type = type;
        this.rental_period = period;
        this.num_miles_driven = miles;
        this.insurance_selected = insurance;
    }

    public String getVehicleType() {
        return vehicle_type;
    }

    public TimePeriod getRentalPeriod() {
        return rental_period;
    }

    public int getNumMilesDriven() {
        return num_miles_driven;
    }

    public boolean isInsuranceSelected() {
        return insurance_selected;
    }

    public String toString() {
        return vehicle_type + ": " + 
               rental_period.toString() + ", " + 
               num_miles_driven + " miles" +
               (insurance_selected ? " (insurance)" : " (no insurance)");
    }
}
