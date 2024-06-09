package org.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
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

    public List<Cashier> getCashiers() {
        return new ArrayList<>(cashiers.values());
    }

    public void addCashier(Cashier cashier) {
        this.cashiers.put(cashier.getId(), cashier);
        this.totalExpenses += cashier.getSalary();
    }
    public void removeCashier(int cashierId) {
        Cashier removed = this.cashiers.remove(cashierId);
        if (removed != null) {
            this.totalExpenses -= removed.getSalary();
        }
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public void setInventory(List<Product> inventory) {
        this.inventory = inventory;
    }

    public List<Receipt> getReceipts() {
        return new ArrayList<>(receipts.values());
    }

    public synchronized void addReceipts(Receipt receipt) {
        this.receipts.put(nextReceiptNumber++, receipt);
        this.totalRevenue += receipt.getTotalAmount();
    }

    public synchronized void addProduct(Product product) {
        this.inventory.add(product);
        this.totalExpenses += product.getUnitDeliveryPrice();
    }

    public synchronized void removeProduct(Product product) {
        this.inventory.remove(product);
    }

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

    public double calculatePriceForFood(double unitDeliveryPrice, LocalDate expirationDate) {
        return foodCalculator.calculateSellingPrice(
                unitDeliveryPrice,
                markupPercentageFood,
                discountPercentage,
                daysBeforeExpirationDiscount,
                expirationDate
        );
    }

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
