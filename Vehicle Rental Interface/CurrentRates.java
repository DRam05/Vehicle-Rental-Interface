public class CurrentRates {
    private VehicleRates[] rates = new VehicleRates[3];

    public CurrentRates(VehicleRates car, VehicleRates suv, VehicleRates van) {
        rates[0] = car;
        rates[1] = suv;
        rates[2] = van;
    }

    public VehicleRates getCarRates() { return rates[0]; }
    public void setCarRates(VehicleRates r) { rates[0] = r; }

    public VehicleRates getSUVRates() { return rates[1]; }
    public void setSUVRates(VehicleRates r) { rates[1] = r; }

    public VehicleRates getMinivanRates() { return rates[2]; }
    public void setMinivanRates(VehicleRates r) { rates[2] = r; }

public double calcEstimatedCost(int vehicleType,
                                TimePeriod estimatedRentalPeriod,
                                int estimatedNumMiles,
                                boolean dailyInsur) {

    VehicleRates r;
    switch (vehicleType) {
        case 1: r = getCarRates(); break;
        case 2: r = getSUVRates(); break;
        case 3: r = getMinivanRates(); break;
        default: return 0.0;
    }

    double cost = 0.0;

    int qty = estimatedRentalPeriod.getQuantity();
    char unit = estimatedRentalPeriod.getUnit();

    if (unit == 'd') {
        cost += qty * r.getDailyRate();
    }
    else if (unit == 'w') {
        cost += qty * r.getWeeklyRate();
    }
    else if (unit == 'm') {
        cost += qty * r.getMonthlyRate();
    }

    cost += estimatedNumMiles * r.getMileageChrg();

    if (dailyInsur) {
        int totalDays = 0;

        if (unit == 'd')
            totalDays = qty;
        else if (unit == 'w')
            totalDays = qty * 7;
        else if (unit == 'm')
            totalDays = qty * 31;  

        cost += totalDays * r.getDailyInsurRate();
    }

    return cost;
}

public double calcActualCost(VehicleRates rates,
                             int num_days_used,
                             int numMilesDriven,
                             boolean dailyInsur) {

    double cost = 0.0;

    int days = num_days_used;

    int months = days / 31;
    cost += months * rates.getMonthlyRate();
    days = days % 31;

    int weeks = days / 7;
    cost += weeks * rates.getWeeklyRate();
    days = days % 7;

    cost += days * (rates.getMonthlyRate() / 31);

    cost += numMilesDriven * rates.getMileageChrg();

    if (dailyInsur) {
        cost += num_days_used * rates.getDailyInsurRate();
    }

    return cost;
}



}
