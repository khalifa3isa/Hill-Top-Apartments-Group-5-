//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class BillingSystemTest {
//
//    @Test
//    public void testGenerateInvoiceHappyPath() {
//        // Arrange
//        BillingSystem billingSystem = new BillingSystem();
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Cleaning", 60, 25.0, "Living room cleaning"));
//        tasks.add(new Task("Laundry", 45, 15.0, "Wash and fold clothes"));
//
//        // Act
//        String invoice = billingSystem.generateInvoice("John Doe", tasks, 10.0); // 10% discount
//
//        // Assert
//        assertTrue(invoice.contains("Cleaning"));
//        assertTrue(invoice.contains("Laundry"));
//        assertTrue(invoice.contains("Discount Applied: -$4.00"));
//        assertTrue(invoice.contains("Total: $36.00"));
//    }
//
//    @Test
//    public void testGenerateInvoiceNoDiscount() {
//        // Arrange
//        BillingSystem billingSystem = new BillingSystem();
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Gardening", 90, 40.0, "Trim the hedges"));
//
//        // Act
//        String invoice = billingSystem.generateInvoice("Jane Doe", tasks, 0.0); // No discount
//
//        // Assert
//        assertTrue(invoice.contains("Gardening"));
//        assertTrue(invoice.contains("Total: $40.00"));
//        assertFalse(invoice.contains("Discount Applied"));
//    }
//
//    @Test
//    public void testGenerateInvoiceInvalidDiscount() {
//        // Arrange
//        BillingSystem billingSystem = new BillingSystem();
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Tutoring", 120, 50.0, "Math tutoring session"));
//
//        // Act & Assert
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            billingSystem.generateInvoice("Tom Smith", tasks, 110.0); // Invalid discount
//        });
//
//        assertEquals("Invalid discount percentage. Must be between 0 and 100.", exception.getMessage());
//    }
//
//    @Test
//    public void testGenerateInvoiceEmptyTaskList() {
//        // Arrange
//        BillingSystem billingSystem = new BillingSystem();
//
//        // Act & Assert
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            billingSystem.generateInvoice("John Doe", new ArrayList<>(), 10.0); // Empty task list
//        });
//
//        assertEquals("No tasks provided for billing.", exception.getMessage());
//    }
//
//    @Test
//    public void testInvoiceStorage() {
//        // Arrange
//        BillingSystem billingSystem = new BillingSystem();
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Repairing", 60, 30.0, "Fix the AC unit"));
//
//        // Act
//        billingSystem.generateInvoice("Alice", tasks, 5.0);
//        List<String> invoices = billingSystem.getInvoices();
//
//        // Assert
//        assertEquals(1, invoices.size());
//        assertTrue(invoices.get(0).contains("Alice"));
//        assertTrue(invoices.get(0).contains("Total: $28.50"));
//    }
//}
