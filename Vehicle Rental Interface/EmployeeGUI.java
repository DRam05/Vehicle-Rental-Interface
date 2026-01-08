import java.util.*;

public class EmployeeGUI implements UserInterface {

    private boolean quit = false;

    public void start() {
    Scanner input = new Scanner(System.in);
    int selection;

    while (!quit) {
        displayMenu();
        selection = getSelection(input);
        execute(selection, input);
    }
}

    private void execute(int selection, Scanner input) {
        int veh_type;
        String vin;
        String[] display_lines;
        RentalDetails rental_details;
        ReservationDetails reserv_details;

        switch (selection) {

            case 1: // display rates
                veh_type = getVehicleType(input);
                switch (veh_type) {
                    case 1: display_lines = SystemInterface.getCarRates(); break;
                    case 2: display_lines = SystemInterface.getSUVRates(); break;
                    case 3: display_lines = SystemInterface.getMinivanRates(); break;
                    default: display_lines = new String[]{"Invalid type."};
                }
                displayResults(display_lines);
                break;

            case 2: // available vehicles
                veh_type = getVehicleType(input);
                switch (veh_type) {
                    case 1: display_lines = SystemInterface.getAvailCars(); break;
                    case 2: display_lines = SystemInterface.getAvailSUVs(); break;
                    case 3: display_lines = SystemInterface.getAvailMinivans(); break;
                    default: display_lines = new String[]{"Invalid type."};
                }
                displayResults(display_lines);
                break;

            case 3: // estimated rental cost
                rental_details = getRentalDetails(input);
                display_lines = SystemInterface.calcRentalCost(rental_details);
                displayResults(display_lines);
                break;

            case 4: // make reservation
                reserv_details = getReservationDetails(input);
                display_lines = SystemInterface.makeReservation(reserv_details);
                displayResults(display_lines);
                break;

            case 5: // cancel reservation
                vin = getVIN(input);
                display_lines = SystemInterface.cancelReservation(vin);
                displayResults(display_lines);
                break;

            case 6: // process returned vehicle
                vin = getVIN(input);
                System.out.print("Days used: ");
                int days = input.nextInt();
                System.out.print("Miles driven: ");
                int miles = input.nextInt();
                display_lines = SystemInterface.processReturnedVehicle(vin, days, miles);
                displayResults(display_lines);
                break;

            case 7: // quit
                quit = true;
                break;

            default:
                System.out.println("Invalid selection.");
        }
    }

    private void displayMenu() {
        System.out.println("\n====== EMPLOYEE MENU ======");
        System.out.println("[1] Display Rental Rates");
        System.out.println("[2] Display Available Vehicles");
        System.out.println("[3] Display Estimated Rental Cost");
        System.out.println("[4] Make a Reservation");
        System.out.println("[5] Cancel a Reservation");
        System.out.println("[6] Process Returned Vehicle");
        System.out.println("[7] Quit");
        System.out.print("Enter selection: ");
    }

    private int getSelection(Scanner input) {
        int sel = input.nextInt();
        while (sel < 1 || sel > 7) {
            System.out.print("Invalid — re-enter: ");
            sel = input.nextInt();
        }
        return sel;
    }

    private String getVIN(Scanner input) {
        System.out.print("Enter VIN: ");
        return input.next();
    }

    private int getVehicleType(Scanner input) {
        System.out.println("Enter vehicle type: 1=Car, 2=SUV, 3=Minivan");
        int type = input.nextInt();
        while (type < 1 || type > 3) {
            System.out.print("Invalid — enter 1, 2, or 3: ");
            type = input.nextInt();
        }
        return type;
    }

    private RentalDetails getRentalDetails(Scanner input) {
        int type = getVehicleType(input);

        System.out.print("Enter rental period unit (d/w/m): ");
        char unit = input.next().toLowerCase().charAt(0);

        System.out.print("Enter quantity: ");
        int qty = input.nextInt();

        TimePeriod tp = new TimePeriod(unit, qty);

        System.out.print("Enter estimated miles: ");
        int miles = input.nextInt();

        System.out.print("Add insurance? (y/n): ");
        boolean ins = input.next().equalsIgnoreCase("y");

        String typeStr = (type == 1) ? "car" : (type == 2 ? "suv" : "minivan");

        return new RentalDetails(typeStr, tp, miles, ins);
    }

    private ReservationDetails getReservationDetails(Scanner input) {
        System.out.print("Customer name: ");
        String name = input.next();

        System.out.print("Credit card: ");
        String cc = input.next();

        String vin = getVIN(input);

        System.out.print("Rental unit (d/w/m): ");
        char u = input.next().charAt(0);

        System.out.print("Quantity: ");
        int q = input.nextInt();

        TimePeriod tp = new TimePeriod(u, q);

        System.out.print("Add insurance? (y/n): ");
        boolean ins = input.next().equalsIgnoreCase("y");

        return new ReservationDetails(name, cc, tp, ins, vin);
    }

    private void displayResults(String[] lines) {
        System.out.println("\n--- RESULTS ---");
        for (String s : lines)
            System.out.println(s);
    }
}
