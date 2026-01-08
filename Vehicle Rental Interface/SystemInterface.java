import java.util.LinkedList;
public class SystemInterface {

	private static CurrentRates agency_rates;
	private static Vehicles agency_vehicles;
	private static Transactions transactions_history;

	// used to init static variables (in place of a constructor)
	public static void initSystem(CurrentRates r, Vehicles v, Transactions t) {
		agency_rates = r;
		agency_vehicles = v;
		transactions_history = t;
	}
	
	// used to check if SystemInterface initialized
	public static boolean initialized() {
    	return agency_rates != null;
}
	
	// Note that methods updateXXXRates, makeReservation and cancelReservation return an
	// acknowledgement of successful completion of the requested action (e.g. “Vehicle ABC123
	// successfully reserved”). Method processReturnedVehicle returns the final cost for the returned 
	// vehicle (e.g., “Total charge for VIN ABC123 for 3 days, 233 miles @  0.15/mile and daily
	// insurance @ 14.95/day = $xxx.xx.)

	// Current Rates Related Methods
	public static String[] getCarRates() {
    VehicleRates r = agency_rates.getCarRates();
    return new String[] { r.toString() };
}

public static String[] getSUVRates() {
    VehicleRates r = agency_rates.getSUVRates();
    return new String[] { r.toString() };
}

public static String[] getMinivanRates() {
    VehicleRates r = agency_rates.getMinivanRates();
    return new String[] { r.toString() };
}


	public static String[] updateCarRates(VehicleRates r) {
    agency_rates.setCarRates(r);
    return new String[] { "Car rates successfully updated." };
}

public static String[] updateSUVRates(VehicleRates r) {
    agency_rates.setSUVRates(r);
    return new String[] { "SUV rates successfully updated." };
}

public static String[] updateMinivanRates(VehicleRates r) {
    agency_rates.setMinivanRates(r);
    return new String[] { "Minivan rates successfully updated." };
}


    public static String[] calcRentalCost(RentalDetails details) {
    int type = 0; // 1 = car, 2 = SUV, 3 = minivan
    switch(details.getVehicleType().toLowerCase()) {
        case "car": type = 1; break;
        case "suv": type = 2; break;
        case "minivan": type = 3; break;
    }

    double cost = agency_rates.calcEstimatedCost(
            type,
            details.getRentalPeriod(),
            details.getNumMilesDriven(),
            details.isInsuranceSelected()
    );

    return new String[] { "Estimated cost: $" + String.format("%.2f", cost) };
}
public static String[] processReturnedVehicle(String vin, int num_days_used, int num_miles_driven) {
    try {
        Vehicle v = agency_vehicles.getVehicle(vin);

        if (!v.isReserved())
            return new String[] { "ERROR: Vehicle is not currently reserved." };

        ReservationDetails r = v.getReservation();
        VehicleRates quoted = v.getQuotedRates();

        // Calculate actual cost
        double total = agency_rates.calcActualCost(
                quoted,
                num_days_used,
                num_miles_driven,
                r.isInsuranceSelected()
        );

        // Record transaction
        transactions_history.add(new Transaction(
                r.getCreditCardNum(),
                r.getCustomerName(),
                v.getDescription(),
                num_days_used + " days",
                num_miles_driven + " miles",
                String.format("%.2f", total)
        ));

        // Clear reservation
        v.cancelReservation();

        return new String[] {
                "Total charge for VIN " + vin + " = $" + String.format("%.2f", total)
        };

    } catch (Exception e) {
        return new String[] { "ERROR: " + e.getMessage() };
    }
}

	// Note that the rates to be used are retrieved from the VehicleRates object stored in the specific rented
	// vehicle object, and the daily insurance option is retrieved from the Reservation object of the rented
	// vehicle

	// Vehicle Related Methods
public static String[] getAvailCars() {
    LinkedList<String> list = new LinkedList<>();
    agency_vehicles.reset();

    while (agency_vehicles.hasNext()) {
        Vehicle v = agency_vehicles.getNext();
        if (v instanceof Car && !v.isReserved())
            list.add(v.toString());
    }
    return list.toArray(new String[0]);
}
	public static String[ ] getAvailSUVs() {
    LinkedList<String> list = new LinkedList<>();
    agency_vehicles.reset();

    while (agency_vehicles.hasNext()) {
        Vehicle v = agency_vehicles.getNext();
        if (v instanceof SUV && !v.isReserved())
            list.add(v.toString());
    }
    return list.toArray(new String[0]);
}
	public static String[ ] getAvailMinivans() {
    LinkedList<String> list = new LinkedList<>();
    agency_vehicles.reset();

    while (agency_vehicles.hasNext()) {
        Vehicle v = agency_vehicles.getNext();
        if (v instanceof Minivan && !v.isReserved())
            list.add(v.toString());
    }
    return list.toArray(new String[0]);
}
public static String[] getAllVehicles() {
    LinkedList<String> list = new LinkedList<>();

    agency_vehicles.reset();   // start at the beginning

    while (agency_vehicles.hasNext()) {
        Vehicle v = agency_vehicles.getNext();
        list.add(v.toString());
    }

    return list.toArray(new String[0]);
}

public static String[] makeReservation(ReservationDetails details) {
    try {
        Vehicle v = agency_vehicles.getVehicle(details.getVIN());

        v.setReservation(details);

        // attach quoted rates at reservation time
        if (v instanceof Car)
            v.setQuotedRates(new VehicleRates(agency_rates.getCarRates()));
        else if (v instanceof SUV)
            v.setQuotedRates(new VehicleRates(agency_rates.getSUVRates()));
        else
            v.setQuotedRates(new VehicleRates(agency_rates.getMinivanRates()));

        return new String[] {
                "Vehicle " + details.getVIN() + " successfully reserved for " + details.getCustomerName()
        };

    } catch (Exception e) {
        return new String[] { "ERROR: " + e.getMessage() };
    }
}
public static String[] cancelReservation(String vin) {
    try {
        Vehicle v = agency_vehicles.getVehicle(vin);
        v.cancelReservation();
        return new String[] { "Reservation for VIN " + vin + " canceled successfully." };
    } catch (Exception e) {
        return new String[] { "ERROR: " + e.getMessage() };
    }
}
public static String[] getReservation(String vin) {
    try {
        Vehicle v = agency_vehicles.getVehicle(vin);
        if (!v.isReserved())
            return new String[] { "No reservation found for VIN " + vin };

        return new String[] { v.getReservation().toString() };

    } catch (Exception e) {
        return new String[] { "ERROR: " + e.getMessage() };
    }
}
public static String[] getAllReservations() {
    LinkedList<String> list = new LinkedList<>();
    agency_vehicles.reset();

    while (agency_vehicles.hasNext()) {
        Vehicle v = agency_vehicles.getNext();
        if (v.isReserved())
            list.add(v.getReservation().toString());
    }
    return list.toArray(new String[0]);
}

	// transactions-related methods
public static String[] addTransaction(Transaction t) {
    transactions_history.add(t);
    return new String[] { "Transaction recorded." };
}
public static String[] getAllTransactions() {
    LinkedList<String> list = new LinkedList<>();

    transactions_history.reset();
    while (transactions_history.hasNext()) {
        list.add(transactions_history.getNext().toString());
    }
    return list.toArray(new String[0]);
}
}