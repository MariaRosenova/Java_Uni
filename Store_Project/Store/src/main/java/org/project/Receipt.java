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

    public static Receipt readFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Receipt) in.readObject();
        }
    }
}
