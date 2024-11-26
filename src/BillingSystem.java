import java.util.ArrayList;
import java.util.List;
/**
 * Handles billing operations, including generating invoices for clients.
 */
public class BillingSystem {
    private List<String> invoices;

    public BillingSystem() {
        invoices = new ArrayList<>();
    }

    /**
     * Generates an invoice for the given client and their tasks.
     * Validates discount percentage to ensure it is within an acceptable range.
     *
     * @param clientName        The name of the client.
     * @param tasks             List of tasks completed for the client.
     * @param discountPercentage Discount to apply to the total (0-100).
     * @return The generated invoice as a string.
     * @throws IllegalArgumentException if discountPercentage is invalid.
     */
    public String generateInvoice(String clientName, List<Task> tasks, double discountPercentage) {
        if (tasks == null || tasks.isEmpty()) {
            throw new IllegalArgumentException("No tasks provided for billing.");
        }

        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Invalid discount percentage. Must be between 0 and 100.");
        }

        StringBuilder invoice = new StringBuilder();
        invoice.append("Client: ").append(clientName).append("\n");
        invoice.append("=========================================\n");

        double totalCost = 0;
        for (Task task : tasks) {
            invoice.append(task.getType())
                    .append(" - ").append(task.getDuration()).append(" mins")
                    .append(" - $").append(task.getCost()).append("\n");
            totalCost += task.getCost();
        }

        // Apply discount if valid
        double discountAmount = (totalCost * discountPercentage) / 100;
        totalCost -= discountAmount;

        invoice.append("-----------------------------------------\n");
        if (discountPercentage > 0) {
            invoice.append("Discount Applied: -$").append(String.format("%.2f", discountAmount)).append("\n");
        }
        invoice.append("Total: $").append(String.format("%.2f", totalCost)).append("\n");

        invoices.add(invoice.toString());
        return invoice.toString();
    }

    /**
     * Retrieves all generated invoices.
     *
     * @return A list of all invoices.
     */
    public List<String> getInvoices() {
        return invoices;
    }
}
