public class TimePeriod {
    private char unit;      // 'd' = days, 'w' = weeks, 'm' = months
    private int quantity;  

    public TimePeriod(char unit, int quantity) {
        this.unit = unit;
        this.quantity = quantity;
    }

    public char getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        switch(unit) {
            case 'd': return quantity + " days";
            case 'w': return quantity + " weeks";
            case 'm': return quantity + " months";
            default:  return quantity + " (unknown unit)";
        }
    }
}
