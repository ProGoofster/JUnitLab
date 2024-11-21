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
}