public class Transaction {
    private String creditcard_num;
    private String customer_name;
    private String vehicle_type;
    private String rental_period;
    private String miles_driven;
    private String rental_cost;

    public Transaction(String cc, String name, String type,
                       String period, String miles, String cost) {
        this.creditcard_num = cc;
        this.customer_name = name;
        this.vehicle_type = type;
        this.rental_period = period;
        this.miles_driven = miles;
        this.rental_cost = cost;
    }

    public String toString() {
        return customer_name + " (" + creditcard_num + "), " + vehicle_type +
               ", " + rental_period + ", " + miles_driven +
               " mi, $" + rental_cost;
    }
}
