import java.util.Scanner;


interface Billable {
    double calculateTotal();
}


class UtilityBill implements Billable {

    private static final double TAX_RATE = 0.18; // 18% GST (India)

    private final String customerName;
    private final int    unitsConsumed;


    public UtilityBill(String customerName, int unitsConsumed) {
        this.customerName  = customerName;
        this.unitsConsumed = unitsConsumed;
    }

    //  Slab-based charge calculation 
    private double calculateCharge() {
        if (unitsConsumed <= 100) {
            // Slab 1: 0 – 100 units → ₹5.00 / unit
            return unitsConsumed * 5.00;

        } else if (unitsConsumed <= 300) {
            // Slab 2: 101 – 300 units → first 100 at ₹5, rest at ₹8
            return (100 * 5.00) + ((unitsConsumed - 100) * 8.00);

        } else {
            // Slab 3: above 300 → first 100 at ₹5, next 200 at ₹8, rest at ₹12
            return (100 * 5.00) + (200 * 8.00) + ((unitsConsumed - 300) * 12.00);
        }
    }

    @Override
    //  Interface method 
    public double calculateTotal() {
        double charge = calculateCharge();
        double tax    = charge * TAX_RATE;
        return charge + tax;
    }

    //  Digital Receipt
    public void printReceipt() {
        double charge = calculateCharge();
        double tax    = charge * TAX_RATE;
        double total  = charge + tax;

        String border = "═".repeat(45);
        System.out.println("\n╔" + border + "╗");
        System.out.println("║           SMARTPAY DIGITAL RECEIPT         ║");
        System.out.println("╠" + border + "╣");
        System.out.printf( "║  Customer Name   : %-25s║%n", customerName);
        System.out.printf( "║  Units Consumed  : %-25d║%n", unitsConsumed);
        System.out.println("╠" + border + "╣");
        System.out.printf( "║  Base Charge     : ₹%-24.2f║%n", charge);
        System.out.printf( "║  GST (18%%)       : ₹%-24.2f║%n", tax);
        System.out.println("╠" + border + "╣");
        System.out.printf( "║  *** FINAL TOTAL : ₹%-24.2f║%n", total);
        System.out.println("╚" + border + "╝\n");
    }
}


public class SmartPay {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

       
        System.out.println("   SMARTPAY – Utility Bill Generator ");
        System.out.println("Type 'Exit' at Customer Name to quit.");

        //  Main loop  keeps running until user types "Exit" 
        while (true) {

            System.out.print("\nEnter Customer Name (or 'Exit' to quit): ");
            String name = sc.nextLine().trim();

            
            if (name.equalsIgnoreCase("Exit")) {
                System.out.println("\nThank you for using SmartPay. Goodbye! 👋");
                break;
            }
            if (name.isEmpty()) {
                System.out.println("⚠  Customer name cannot be empty. Please try again.");
                continue;
            }

            //  Meter readings
            int previous, current;

            try {
                System.out.print("Enter Previous Meter Reading (units): ");
                previous = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Enter Current  Meter Reading (units): ");
                current  = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠  Invalid input! Meter readings must be whole numbers.");
                continue;
            }
            if (previous < 0 || current < 0) {
                System.out.println("⚠  Meter readings cannot be negative.");
                continue;
            }

            if (previous > current) {
                System.out.println("⚠  ERROR: Previous reading (" + previous
                        + ") cannot be greater than current reading (" + current
                        + "). Please verify the meter values.");
                continue;
            }

            // Calculate units consumed 
            int units = current - previous;

            if (units == 0) {
                System.out.println("ℹ  No units consumed this billing cycle. No bill generated.");
                continue;
            }

            //  Generate bill 
            UtilityBill bill = new UtilityBill(name, units);
            bill.printReceipt();

            System.out.printf("   [Billable.calculateTotal() returned: ₹%.2f]%n", bill.calculateTotal());
        }

        sc.close();
    }
}
