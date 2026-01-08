import java.util.*;

public class ManagerGUI implements UserInterface {

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

        String[] lines;
        switch (selection) {

        case 1: // display/update current rates
            updateRatesMenu(input);
            break;

        case 2: // display all vehicles (reserved or unreserved)
            lines = SystemInterface.getAllVehicles();
            displayResults(lines);
            break;

        case 3: // display all reservations
            lines = SystemInterface.getAllReservations();
            displayResults(lines);
            break;

        case 4: // display all transactions
            lines = SystemInterface.getAllTransactions();
            displayResults(lines);
            break;

        case 5: // quit
            quit = true;
            break;

        default:
            System.out.println("Invalid selection.");
        }
    }

    private void displayMenu() {
        System.out.println("\n====== MANAGER MENU ======");
        System.out.println("[1] Display / Update Rental Rates");
        System.out.println("[2] Display ALL Vehicles");
        System.out.println("[3] Display ALL Reservations");
        System.out.println("[4] Display ALL Transactions");
        System.out.println("[5] Quit");
        System.out.print("Enter selection: ");
    }

    private int getSelection(Scanner input) {
        int sel = input.nextInt();
        while (sel < 1 || sel > 5) {
            System.out.print("Invalid — re-enter: ");
            sel = input.nextInt();
        }
        return sel;
    }

    private void updateRatesMenu(Scanner input) {

        System.out.println("\n--- Update Rates ---");
        System.out.println("[1] Display Current Rates");
        System.out.println("[2] Update Car Rates");
        System.out.println("[3] Update SUV Rates");
        System.out.println("[4] Update Minivan Rates");
        System.out.print("Enter selection: ");

        int choice = input.nextInt();

        switch (choice) {

        case 1:
            displayResults(new String[]{
                "Car: " + SystemInterface.getCarRates()[0],
                "SUV: " + SystemInterface.getSUVRates()[0],
                "Minivan: " + SystemInterface.getMinivanRates()[0]
            });
            break;

        case 2:
            VehicleRates newCarRates = enterRates(input);
            displayResults(SystemInterface.updateCarRates(newCarRates));
            break;

        case 3:
            VehicleRates newSuvRates = enterRates(input);
            displayResults(SystemInterface.updateSUVRates(newSuvRates));
            break;

        case 4:
            VehicleRates newMinivanRates = enterRates(input);
            displayResults(SystemInterface.updateMinivanRates(newMinivanRates));
            break;

        default:
            System.out.println("Returning to Manager Menu...");
        }
    }

    private VehicleRates enterRates(Scanner input) {
        System.out.print("Enter Daily Rate: ");
        double daily = input.nextDouble();

        System.out.print("Enter Weekly Rate: ");
        double weekly = input.nextDouble();

        System.out.print("Enter Monthly Rate: ");
        double monthly = input.nextDouble();

        System.out.print("Enter Per-Mile Charge: ");
        double mile = input.nextDouble();

        System.out.print("Enter Daily Insurance Rate: ");
        double insur = input.nextDouble();

        return new VehicleRates(daily, weekly, monthly, mile, insur);
    }

    private void displayResults(String[] lines) {
        System.out.println("\n--- RESULTS ---");
        if (lines == null || lines.length == 0) {
            System.out.println("[No entries found]");
            return;
        }

        for (String s : lines)
            System.out.println(s);
    }
}
