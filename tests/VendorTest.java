import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {
    Vendor vendor = new Vendor();
    //test case to validate that you can add money to a vendor
    @Test
    void addMoney() {
        vendor.addMoney(50);
        assertEquals(50, vendor.getBalance());
    }

    @Test
    void addMoneyNeg() {
        vendor.addMoney(-500);
        assertEquals(0, vendor.getBalance());
    }

    @Test
    void addMoneyFloatLim() {
        vendor.addMoney(Float.MAX_VALUE);
        vendor.addMoney(25);

        assertEquals(Float.MAX_VALUE, vendor.getBalance());
    }

    @Test
    void addMoneyFloatLimNeg() {
        vendor.addMoney(Float.MIN_VALUE);
        vendor.addMoney(25);

        assertEquals(25, vendor.getBalance());
    }

    //test to validate that you can buy an item from a vendor
    @Test
    void buyItem() {
        vendor.addMoney(10);
        vendor.stockVendor("Gum", 50, .5);
        vendor.select("Gum");

        assertEquals(49, vendor.getStock("Gum"));
        assertEquals(9.5, vendor.getBalance());
    }

    @Test
    void buyNegItem() {
        vendor.addMoney(100);
        vendor.stockVendor("Stuff", 50, .5);
        vendor.select("Stuff", -50);

        assertEquals(50, vendor.getStock("Stuff"));
    }

    //test to validate that you can empty the vendor’s inventory
    @Test
    void emptyInventory() {
        int amt = 50;
        vendor.addMoney(25);
        vendor.stockVendor("Gum", amt, .5);

        for(int i = amt; i > 0; i--){
            assertEquals(i, vendor.getStock("Gum"));
            vendor.select("Gum");
        };

        assertEquals(0, vendor.getStock("Gum"));
        assertEquals(0, vendor.getBalance());
    }
}