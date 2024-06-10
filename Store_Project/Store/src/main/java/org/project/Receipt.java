package org.project;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Receipt implements Serializable {
    private int receiptNumber = 0;
    private static int lastReceiptNumber = 0;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Product> products;
    private List<Double> prices;
    private Store store;
    private double totalAmount;

    public Receipt(Cashier cashier, Store store) {
        this.receiptNumber = generateNextReceiptNumber();
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.products = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.store = store;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


    /**
     * Adds a product to the receipt.
     *
     * @param product  the product to add
     * @param quantity the quantity of the product
     * @throws InsufficientQuantityException if the product quantity is insufficient
     * @throws ExpiredProductException       if the product is expired
     */

    public void addProduct(Product product, int quantity) throws InsufficientQuantityException, ExpiredProductException {
        if (product.getQuantity() < quantity) {
            throw new InsufficientQuantityException(product, quantity, product.getQuantity());
        }

        if (product.getExpirationDate() != null && product.getExpirationDate().isBefore(LocalDate.now())) {
            throw new ExpiredProductException(product);
        }

        product.setQuantity(product.getQuantity() - quantity);
        products.add(product);

        double price;
        if (product.getCategory() == Product.Category.FOOD) {
            price = store.calculatePriceForFood(product.getUnitDeliveryPrice(), product.getExpirationDate());
        } else {
            price = store.calculatePriceForNonFood(product.getUnitDeliveryPrice());
        }

        prices.add(price * quantity);
        calculateTotal();
    }

    private static synchronized int generateNextReceiptNumber() {
        return ++lastReceiptNumber;
    }

    /**
     * Calculates the total amount for the receipt.
     */

    public void calculateTotal() {
        totalAmount = prices.stream().mapToDouble(Double::doubleValue).sum();
    }

    public void printReceipt() {
        System.out.println("Receipt #" + receiptNumber);
        System.out.println("Cashier: " + cashier.getName());
        System.out.println("Date: " + dateTime);
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            double price = prices.get(i);
            System.out.println(product.getName() + " - " + price);
        }
        System.out.println("Total: " + totalAmount);
    }

    /**
     * Saves the receipt to a file.
     *
     * @throws IOException if an I/O error occurs
     */

    public void saveToFile() throws IOException {
        String fileName = "receipt_" + receiptNumber + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Receipt #" + receiptNumber + "\n");
            writer.write("Cashier: " + cashier.getName() + "\n");
            writer.write("Date: " + dateTime + "\n");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                double price = prices.get(i);
                writer.write(product.getName() + " - " + price + "\n");
            }
            writer.write("Total: " + totalAmount + "\n");
        }
    }

    /**
     * Reads a receipt from a file.
     *
     * @param fileName the name of the file
     * @return the read receipt
     * @throws IOException            if an I/O error occurs
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */

    public static Receipt readFromFile(String fileName, Store store, Cashier cashier) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Receipt receipt = new Receipt(cashier, store);
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Receipt #")) {
                    receipt.receiptNumber = Integer.parseInt(line.split("#")[1].trim());
                } else if (line.startsWith("Cashier:")) {
                    // Ignore, as cashier is already passed
                } else if (line.startsWith("Date:")) {
                    // Ignore, as dateTime is set to now
                } else if (line.startsWith("Total:")) {
                    receipt.totalAmount = Double.parseDouble(line.split(":")[1].trim());
                } else {
                    String[] parts = line.split(" - ");
                    if (parts.length == 2) {
                        String productName = parts[0].trim();
                        double price = Double.parseDouble(parts[1].trim());
                        Product product = store.getInventory().stream()
                                .filter(p -> p.getName().equals(productName))
                                .findFirst()
                                .orElse(null);
                        if (product != null) {
                            receipt.products.add(product);
                            receipt.prices.add(price);
                        }
                    }
                }
            }
            receipt.calculateTotal();
            return receipt;
        }
    }

    public Store getStore() {
        return store;
    }
}
