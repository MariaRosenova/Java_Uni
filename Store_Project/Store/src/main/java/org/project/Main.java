package org.project;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws InsufficientFundsException {
        Store store = new Store();

        Cashier cashier1 = new Cashier(1, "Peter Petrov", 2500.00);
        Cashier cashier2 = new Cashier(2, "Alex Ivanova", 2700.00);

        store.addCashier(cashier1);
        store.addCashier(cashier2);

        store.setMarkupPercentageFood(20);
        store.setMarkupPercentageNonFood(10);
        store.setDaysBeforeExpirationDiscount(5);  // set 5 days before expiration
        store.setDiscountPercentage(10); //%

        Product product1 = new Product("Apple", 20, Product.Category.FOOD, LocalDate.of(2025, 12, 31), 20);
        Product product2 = new Product("Cake", 10, Product.Category.FOOD, LocalDate.now().plusDays(5), 10);
        Product product3 = new Product("Book", 30.0, Product.Category.NON_FOOD, null, 20);

        store.addProduct(product1);
        store.addProduct(product2);
        store.addProduct(product3);

        Customer customer = new Customer("Maria Shishkova", 10000);


        // Create a receipt
        Receipt receipt = new Receipt(cashier1, store);

        try {
            receipt.addProduct(product1, 2);
            receipt.addProduct(product2, 1);
            receipt.addProduct(product3, 1);
        } catch (InsufficientQuantityException | ExpiredProductException e) {
            System.out.println("Error adding product: " + e.getMessage());
            return;  // Exit if there is an error adding products
        }

        receipt.calculateTotal();


        try {
            if (cashier1.processOrder(receipt.getTotalAmount(), customer.getBalance())) {
                receipt.printReceipt();

                // Save the receipt to a file
                try {
                    receipt.saveToFile();
                } catch (IOException e) {
                    System.out.println("Error saving receipt to file: " + e.getMessage());
                }

                // Read the receipt from a file
                try {
                    Receipt loadedReceipt = Receipt.readFromFile("receipt_" + receipt.getReceiptNumber() + ".txt", store, cashier1);
                    System.out.println("Loaded receipt from file:");
                    loadedReceipt.printReceipt();
                } catch (IOException e) {
                    System.out.println("Error reading receipt from file: " + e.getMessage());
                }
            } else {
                System.out.println("Insufficient funds. Please try again.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
