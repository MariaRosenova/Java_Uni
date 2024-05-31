package org.project;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Create instances of SellingPriceCalculator
        SellingPriceCalculator foodCalculator = new FoodSellingPriceCalculator();
        SellingPriceCalculator nonFoodCalculator = new NonFoodSellingPriceCalculator();

        // Injecting the dependencies into Product instances
        Product foodProduct = new Product(1, "Apple", 100, "Food", LocalDate.now().plusDays(5), 10, foodCalculator);
        Product nonFoodProduct = new Product(2, "Book", 50, "NonFood", null, 20, nonFoodCalculator);

        // Calculate selling prices
        double foodSellingPrice = foodProduct.calculateSellingPrice(20, 3, 10);
        double nonFoodSellingPrice = nonFoodProduct.calculateSellingPrice(20, 0, 0);

        // Output results
        System.out.println("Food selling price: " + foodSellingPrice);
        System.out.println("Non-food selling price: " + nonFoodSellingPrice);
    }
}