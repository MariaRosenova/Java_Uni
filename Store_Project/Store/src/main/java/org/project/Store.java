package org.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents a store that manages products, cashiers, and receipts.
 */

public class Store {
    private static final Logger LOGGER = Logger.getLogger(Store.class.getName());
    private static int nextReceiptNumber = 1;
    private Map<Integer, Cashier> cashiers;
    private List<Product> inventory;
    private Map<Integer, Receipt> receipts;

    private int markupPercentageFood;
    private int markupPercentageNonFood;
    private int daysBeforeExpirationDiscount;
    private int discountPercentage;
    private double totalRevenue;
    private double totalExpenses;

    private SellingPriceCalculator foodCalculator;
    private SellingPriceCalculator nonFoodCalculator;

    public Store() {
        this.cashiers = new HashMap<>();
        this.inventory = new ArrayList<>();
        this.receipts = new HashMap<>();

        this.markupPercentageFood = 20;
        this.markupPercentageNonFood = 30;
        this.daysBeforeExpirationDiscount = 5;
        this.discountPercentage = 10;

        this.totalRevenue = 0.0;
        this.totalExpenses = 0.0;

        this.foodCalculator = new FoodSellingPriceCalculator();
        this.nonFoodCalculator = new NonFoodSellingPriceCalculator();
    }

    /**
     * Gets the list of cashiers in the store.
     *
     * @return a list of cashiers.
     */

    public List<Cashier> getCashiers() {
        return new ArrayList<>(cashiers.values());
    }

    /**
     * Adds a cashier to the store.
     *
     * @param cashier the cashier to add.
     */

    public void addCashier(Cashier cashier) {
        if (cashier == null) {
            LOGGER.log(Level.WARNING, "Attempted to add a null cashier.");
            return;
        }
        this.cashiers.put(cashier.getId(), cashier);
        this.totalExpenses += cashier.getSalary();
        LOGGER.log(Level.INFO, "Added cashier: " + cashier.getName());
    }

    /**
     * Removes a cashier from the store by ID.
     *
     * @param cashierId the ID of the cashier to remove.
     */

    public void removeCashier(int cashierId) {
        Cashier removed = this.cashiers.remove(cashierId);
        if (removed != null) {
            this.totalExpenses -= removed.getSalary();
            LOGGER.log(Level.INFO, "Removed cashier: " + removed.getName());
        } else {
            LOGGER.log(Level.WARNING, "Attempted to remove a non-existent cashier with ID: " + cashierId);
        }
    }

    /**
     * Gets the inventory of the store.
     *
     * @return the inventory list.
     */

    public List<Product> getInventory() {
        return inventory;
    }

    /**
     * Sets the inventory of the store.
     *
     * @param inventory the new inventory list.
     */

    public void setInventory(List<Product> inventory) {
        if (inventory == null) {
            LOGGER.log(Level.WARNING, "Attempted to set null inventory.");
            return;
        }
        this.inventory = inventory;
    }

    /**
     * Gets the list of receipts.
     *
     * @return the list of receipts.
     */

    public List<Receipt> getReceipts() {
        return new ArrayList<>(receipts.values());
    }

    public synchronized void addReceipts(Receipt receipt) {
        if (receipt == null) {
            LOGGER.log(Level.WARNING, "Attempted to add a null receipt.");
            return;
        }
        this.receipts.put(nextReceiptNumber++, receipt);
        this.totalRevenue += receipt.getTotalAmount();
    }

    /**
     * Adds a product to the store's inventory and updates the total expenses.
     *
     * @param product the product to add.
     */
    public synchronized void addProduct(Product product) {
        if (product == null) {
            LOGGER.log(Level.WARNING, "Attempted to add a null product.");
            return;
        }

        if (product.getUnitDeliveryPrice() < 0) {
            LOGGER.log(Level.WARNING, "Attempted to add a product with negative delivery price.");
            return;
        }
        this.inventory.add(product);
        this.totalExpenses += product.getUnitDeliveryPrice();
        LOGGER.log(Level.INFO, "Added product: " + product.getName());
    }

    /**
     * Removes a product from the store's inventory.
     *
     * @param product the product to remove.
     */
    public synchronized void removeProduct(Product product) {
        if (product == null) {
            LOGGER.log(Level.WARNING, "Attempted to remove a null product.");
            return;
        }
        this.inventory.remove(product);
        LOGGER.log(Level.INFO, "Removed product: " + product.getName());
    }

    /**
     * Gets the markup percentage for food products.
     *
     * @return the markup percentage for food.
     */

    public int getMarkupPercentageFood() {
        return markupPercentageFood;
    }

    public int getMarkupPercentageNonFood() {
        return markupPercentageNonFood;
    }

    public int getDaysBeforeExpirationDiscount() {
        return daysBeforeExpirationDiscount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }


    public void setMarkupPercentageFood(int markupPercentageFood) {
        this.markupPercentageFood = markupPercentageFood;
    }

    public void setMarkupPercentageNonFood(int markupPercentageNonFood) {
        this.markupPercentageNonFood = markupPercentageNonFood;
    }

    public void setDaysBeforeExpirationDiscount(int daysBeforeExpirationDiscount) {
        this.daysBeforeExpirationDiscount = daysBeforeExpirationDiscount;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getProfit() {
        return totalRevenue - totalExpenses;
    }

    /**
     * Calculates the selling price for food products.
     *
     * @param unitDeliveryPrice the delivery price per unit.
     * @param expirationDate the expiration date of the product.
     * @return the calculated selling price.
     */

    public double calculatePriceForFood(double unitDeliveryPrice, LocalDate expirationDate) {
        return foodCalculator.calculateSellingPrice(
                unitDeliveryPrice,
                markupPercentageFood,
                discountPercentage,
                daysBeforeExpirationDiscount,
                expirationDate
        );
    }

    /**
     * Calculates the selling price for non-food products.
     *
     * @param unitDeliveryPrice the delivery price per unit.
     * @return the calculated selling price.
     */
    public double calculatePriceForNonFood(double unitDeliveryPrice) {
        return nonFoodCalculator.calculateSellingPrice(
                unitDeliveryPrice,
                markupPercentageNonFood,
                0, // No discount for non-food
                0, // No days before expiration for non-food
                LocalDate.now() // No expiration date for non-food
        );
    }
}
