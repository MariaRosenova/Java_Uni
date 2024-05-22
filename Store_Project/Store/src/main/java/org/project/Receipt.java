package org.project;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Receipt implements Serializable {
    private int receiptNumber;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Product> products;
    private double totalAmount;

    public Receipt(int receiptNumber, Cashier cashier) {
        this.receiptNumber = receiptNumber;
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) throws InsufficientQuantityException, ExpiredProductException {
        if (product.getQuantity() < quantity) {
            throw new InsufficientQuantityException(product, quantity, product.getQuantity());
        }

        if (product.getExpirationDate().isBefore(LocalDate.now())) {
            throw new ExpiredProductException(product);
        }

        product.setQuantity(product.getQuantity() - quantity);
        products.add(product);
    }

    public void calculateTotal() {
        totalAmount = products.stream().mapToDouble(p -> p.calculateSellingPrice(20, 5, 10)).sum(); // Example percentages
    }

    public void printReceipt() {
        System.out.println("Receipt #" + receiptNumber);
        System.out.println("Cashier: " + cashier.getName());
        System.out.println("Date: " + dateTime);
        for (Product product : products) {
            System.out.println(product.getName() + " - " + product.calculateSellingPrice(20, 5, 10));
        }
        System.out.println("Total: " + totalAmount);
    }

    public void saveToFile() {
        // Serialization code here
    }
}
