public class VehicleRates {
    private double daily;
    private double weekly;
    private double monthly;
    private double perMile;
    private double dailyInsur;

    public VehicleRates(double d, double w, double m, double pm, double di) {
        this.daily = d;
        this.weekly = w;
        this.monthly = m;
        this.perMile = pm;
        this.dailyInsur = di;
    }

    public VehicleRates(VehicleRates other) {
        this.daily = other.daily;
        this.weekly = other.weekly;
        this.monthly = other.monthly;
        this.perMile = other.perMile;
        this.dailyInsur = other.dailyInsur;
    }

    public double getDailyRate() { return daily; }
    public double getWeeklyRate() { return weekly; }
    public double getMonthlyRate() { return monthly; }
    public double getMileageChrg() { return perMile; }
    public double getDailyInsurRate() { return dailyInsur; }

    public String toString() {
        return "Daily: $" + daily + ", Weekly: $" + weekly + ", Monthly: $" +
               monthly + ", Per Mile: $" + perMile + ", Insurance: $" + dailyInsur;
    }
}
