public class ReservationDetails {
    private String customer_name;
    private String credit_card_num;
    private TimePeriod rental_period;
    private boolean insurance_selected;
    private String VIN;

    public ReservationDetails(String name, String card, TimePeriod period,
                              boolean insurance, String vin) {
        this.customer_name = name;
        this.credit_card_num = card;
        this.rental_period = period;
        this.insurance_selected = insurance;
        this.VIN = vin;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public String getCreditCardNum() {
        return credit_card_num;
    }

    public TimePeriod getRentalPeriod() {
        return rental_period;
    }

    public boolean isInsuranceSelected() {
        return insurance_selected;
    }

    public String getVIN() {
        return VIN;
    }

    public String toString() {
        return "Reservation for " + customer_name + 
               " (card " + credit_card_num + ") " +
               rental_period.toString() +
               (insurance_selected ? " with insurance" : " without insurance") +
               ", VIN: " + VIN;
    }
}
