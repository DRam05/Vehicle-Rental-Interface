import java.util.*;

public class AgencyRentalProgram {

    public static void main(String[] args) {

        CurrentRates agency_rates = new CurrentRates(
            new VehicleRates(24.95, 169.95, 514.95, 0.16, 14.95),  // Car
            new VehicleRates(29.95, 189.95, 679.95, 0.16, 14.95),  // SUV
            new VehicleRates(36.95, 224.95, 687.95, 0.21, 19.95)   // Minivan
        );

        Vehicles agency_vehicles = new Vehicles();
        populate(agency_vehicles);

        Transactions transactions = new Transactions();

        Scanner input = new Scanner(System.in);
        UserInterface ui;
        boolean quit = false;

        while (!quit) {

            ui = getUI(input);

            if (ui == null) {
                quit = true;
            } else {

                if (!SystemInterface.initialized()) {
                    SystemInterface.initSystem(agency_rates, agency_vehicles, transactions);
                }

                ui.start();
            }
        }
    }

    public static UserInterface getUI(Scanner input) {
        int selection;

        while (true) {
            System.out.print("1 – Employee, 2 – Manager, 3 – quit: ");
            selection = input.nextInt();

            switch (selection) {
                case 1: return new EmployeeGUI();
                case 2: return new ManagerGUI();
                case 3: return null;
                default:
                    System.out.println("Invalid selection – please reenter");
            }
        }
    }

    private static void populate(Vehicles v) {

        // Cars
        v.add(new Car("Toyota Prius", 57, "AED456"));
        v.add(new Car("Honda Insight", 55, "DEF123"));
        v.add(new Car("Hyundai Elantra Hybrid", 53, "JHK857"));

        // SUVs
        v.add(new SUV("Toyota RAV4 Hybrid", 39, 6, "DPF450"));
        v.add(new SUV("Ford Explorer Hybrid", 31, 7, "WCH302"));
        v.add(new SUV("Honda Pilot Hybrid", 36, 7, "KSB698"));
        v.add(new SUV("Lexus NX 450h+", 37, 6, "GEK334"));

        // Minivans
        v.add(new Minivan("Toyota Sienna", 36, 9, "AGH890"));
        v.add(new Minivan("Chrysler Pacifica Hybrid", 82, 9, "BFJ386"));
        v.add(new Minivan("Honda Odyssey", 22, 8, "KCM341"));
        v.add(new Minivan("Kia Carnival", 22, 8, "TSH580"));
    }
}
