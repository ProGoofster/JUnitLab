public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Ultra Shop!");



        System.out.println("Maintained by <A human>");

        //As a User, I would like for the vendor system to manage and print the inventory of 5
        //different vendors so that I can have multiple vendors available.
        Vendor vendor1 = new Vendor(21, 19);
        Vendor vendor2 = new Vendor(35, 36);
        Vendor vendor3 = new Vendor(28, 29);
        Vendor vendor4 = new Vendor(72, 57);
        Vendor vendor5 = new Vendor(38, 74);

        System.out.println("Vendor 1: ");
        vendor1.printStock();
        System.out.println("Vendor 2: ");
        vendor2.printStock();
        System.out.println("Vendor 3: ");
        vendor3.printStock();
        System.out.println("Vendor 4: ");
        vendor4.printStock();
        System.out.println("Vendor 5: ");
        vendor5.printStock();

    }
}