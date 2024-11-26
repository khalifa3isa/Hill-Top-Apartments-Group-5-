import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BillingSystemTest {

    @Test
    public void testGenerateInvoiceWithValidDiscount() {
        // Arrange
        BillingSystem billingSystem = new BillingSystem();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Cleaning", 60, 25.0, "Living room cleaning"));
        tasks.add(new Task("Laundry", 45, 15.0, "Wash and fold clothes"));

        // Act
        String invoice = billingSystem.generateInvoice("John Doe", tasks, 10.0); // 10% discount

        // Assert
        System.out.println("Generated Invoice:\n" + invoice);
        assertTrue(invoice.contains("Cleaning"), "Invoice should include the task 'Cleaning'.");
        assertTrue(invoice.contains("Laundry"), "Invoice should include the task 'Laundry'.");
        assertTrue(invoice.contains("Discount Applied: -$4.00"), "Invoice should show a 10% discount applied.");
        assertTrue(invoice.contains("Total: $36.00"), "Invoice should show the correct total after discount.");
    }

    @Test
    public void testGenerateInvoiceWithoutDiscount() {
        // Arrange
        BillingSystem billingSystem = new BillingSystem();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Gardening", 90, 40.0, "Trim the hedges"));

        // Act
        String invoice = billingSystem.generateInvoice("Jane Doe", tasks, 0.0); // No discount

        // Assert
        System.out.println("Generated Invoice (No Discount):\n" + invoice);
        assertTrue(invoice.contains("Gardening"), "Invoice should include the task 'Gardening'.");
        assertTrue(invoice.contains("Total: $40.00"), "Invoice should show the total without any discount.");
        assertFalse(invoice.contains("Discount Applied"), "Invoice should not include a discount line when no discount is applied.");
    }

    @Test
    public void testGenerateInvoiceWithInvalidDiscount() {
        // Arrange
        BillingSystem billingSystem = new BillingSystem();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Tutoring", 120, 50.0, "Math tutoring session"));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            billingSystem.generateInvoice("Tom Smith", tasks, 110.0); // Invalid discount
        });

        System.out.println("Caught Exception for Invalid Discount: " + exception.getMessage());
        assertEquals("Invalid discount percentage. Must be between 0 and 100.", exception.getMessage(),
                "Expected exception message does not match actual message.");
    }

    @Test
    public void testGenerateInvoiceWithEmptyTaskList() {
        // Arrange
        BillingSystem billingSystem = new BillingSystem();

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            billingSystem.generateInvoice("John Doe", new ArrayList<>(), 10.0); // Empty task list
        });

        System.out.println("Caught Exception for Empty Task List: " + exception.getMessage());
        assertEquals("No tasks provided for billing.", exception.getMessage(),
                "Expected exception message does not match actual message.");
    }

    @Test
    public void testInvoiceStorageAfterGeneration() {
        // Arrange
        BillingSystem billingSystem = new BillingSystem();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Repairing", 60, 30.0, "Fix the AC unit"));

        // Act
        billingSystem.generateInvoice("Alice", tasks, 5.0);
        List<String> invoices = billingSystem.getInvoices();

        // Assert
        assertEquals(1, invoices.size(), "Invoice storage should contain one invoice.");
        System.out.println("Stored Invoice:\n" + invoices.get(0));
        assertTrue(invoices.get(0).contains("Alice"), "Stored invoice should include the client name 'Alice'.");
        assertTrue(invoices.get(0).contains("Total: $28.50"), "Stored invoice should show the correct discounted total.");
    }
}
