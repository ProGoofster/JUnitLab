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

    //test to validate that you can buy an item from a vendor
    @Test
    void buyItem() {
        vendor.addMoney(10);
        vendor.stockVendor("Gum", 50, .5);
        vendor.select("Gum");

        assertEquals(49, vendor.getStock("Gum"));
        assertEquals(9.5, vendor.getBalance());
    }
}