//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;

public class BillingSystem {
    private List<String> invoices = new ArrayList();

    public BillingSystem() {
    }

    public String generateInvoice(String clientName, List<Task> tasks, double discountPercentage) {
        if (tasks != null && !tasks.isEmpty()) {
            if (!(discountPercentage < (double)0.0F) && !(discountPercentage > (double)100.0F)) {
                StringBuilder invoice = new StringBuilder();
                invoice.append("Client: ").append(clientName).append("\n");
                invoice.append("=========================================\n");
                double totalCost = (double)0.0F;

                for(Task task : tasks) {
                    invoice.append(task.getType()).append(" - ").append(task.getDuration()).append(" mins").append(" - $").append(task.getCost()).append("\n");
                    totalCost += task.getCost();
                }

                double discountAmount = totalCost * discountPercentage / (double)100.0F;
                totalCost -= discountAmount;
                invoice.append("-----------------------------------------\n");
                if (discountPercentage > (double)0.0F) {
                    invoice.append("Discount Applied: -$").append(String.format("%.2f", discountAmount)).append("\n");
                }

                invoice.append("Total: $").append(String.format("%.2f", totalCost)).append("\n");
                this.invoices.add(invoice.toString());
                return invoice.toString();
            } else {
                throw new IllegalArgumentException("Invalid discount percentage. Must be between 0 and 100.");
            }
        } else {
            throw new IllegalArgumentException("No tasks provided for billing.");
        }
    }

    public List<String> getInvoices() {
        return this.invoices;
    }
}
