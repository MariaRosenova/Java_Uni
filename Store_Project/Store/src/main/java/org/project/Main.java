package org.project;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Създаване на обект Store
        Store store = new Store();

        SellingPriceCalculator foodCalculator = new FoodSellingPriceCalculator();
        SellingPriceCalculator nonFoodCalculator = new NonFoodSellingPriceCalculator();

        // Създаване на касиери
        Cashier cashier1 = new Cashier(1, "Peter Petrov", 2500.00);
        Cashier cashier2 = new Cashier(2, "Alex Ivanova", 2700.00);

        // Добавяне на касиери в магазина
        store.addCashier(cashier1);
        store.addCashier(cashier2);

        Product product1 = new Product("Apple", 20, Product.Category.FOOD, LocalDate.of(2025, 12, 31),10, foodCalculator);
        Product product2 = new Product("Cake", 10, Product.Category.FOOD, LocalDate.now().plusDays(5), 10, foodCalculator);
        Product product3 = new Product("Book", 30.0, Product.Category.NON_FOOD, null, 1, nonFoodCalculator);

        // Calculate selling prices
        double foodSellingPrice = foodProduct.calculateSellingPrice(20, 3, 10);
        double nonFoodSellingPrice = nonFoodProduct.calculateSellingPrice(20, 0, 0);



        // Output results
        System.out.println("Food selling price: " + foodSellingPrice);
        System.out.println("Non-food selling price: " + nonFoodSellingPrice);

        // Save the receipt to a file
        try {
            receipt.saveToFile();
        } catch (IOException e) {
            System.out.println("Error saving receipt to file: " + e.getMessage());
        }

        // Read the receipt from a file
        try {
            Receipt loadedReceipt = Receipt.readFromFile("receipt_1.txt");
            System.out.println("Loaded receipt from file:");
            loadedReceipt.printReceipt();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading receipt from file: " + e.getMessage());
        }
    }
}