package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BillingSystemTest {
    @Test
    public void testGenerateInvoice() {
        Task[] tasks = { new Task("Bathing", 60, 25.0) };
        BillingSystem billingSystem = new BillingSystem();
        assertDoesNotThrow(() -> billingSystem.generateInvoice("John Doe", tasks));
    }
}
