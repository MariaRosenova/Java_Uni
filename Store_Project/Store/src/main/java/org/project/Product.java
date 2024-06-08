package org.project;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {

    private int id;
    private static int idCode = 0;
    private String name;
    private double unitDeliveryPrice;
    private Category category;
    private LocalDate expirationDate;
    private int quantity;
    private SellingPriceCalculator sellingPriceCalculator;

    public enum Category {
        FOOD,
        NON_FOOD
    }
    public Product(String name, double unitDeliveryPrice, Category category, LocalDate expirationDate, int quantity, SellingPriceCalculator sellingPriceCalculator)  {

        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (unitDeliveryPrice < 0) throw new IllegalArgumentException("Unit delivery price cannot be negative");
        if (category == null) throw new IllegalArgumentException("Category cannot be null");
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        if (sellingPriceCalculator == null) throw new IllegalArgumentException("Selling price calculator cannot be null");


        this.id = generateId();
        this.name = name;
        this.unitDeliveryPrice = unitDeliveryPrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.sellingPriceCalculator = sellingPriceCalculator;
    }

    private static synchronized int generateId() { return ++idCode; }

    public double calculateSellingPrice(int markupPercentage, int daysBeforeExpiration, int discountPercentage) {
        return sellingPriceCalculator.calculateSellingPrice(unitDeliveryPrice, markupPercentage, discountPercentage, daysBeforeExpiration, expirationDate);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getUnitDeliveryPrice() {
        return unitDeliveryPrice;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    public void setUnitDeliveryPrice(double unitDeliveryPrice) {
        if (unitDeliveryPrice < 0) throw new IllegalArgumentException("Unit delivery price cannot be negative");
        this.unitDeliveryPrice = unitDeliveryPrice;
    }

    public void setCategory(Category category) {
        if (category == null) throw new IllegalArgumentException("Category cannot be null");
        this.category = category;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) throw new IllegalArgumentException("Expiration date cannot be null");
        this.expirationDate = expirationDate;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitDeliveryPrice=" + unitDeliveryPrice +
                ", category='" + category + '\'' +
                ", expirationDate=" + expirationDate +
                ", quantity=" + quantity +
                ", sellingPriceCalculator=" + sellingPriceCalculator +
                '}';
    }
}
