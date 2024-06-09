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

    public enum Category {
        FOOD,
        NON_FOOD
    }

    public Product(String name, double unitDeliveryPrice, Category category, LocalDate expirationDate, int quantity)  {

        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (unitDeliveryPrice < 0) throw new IllegalArgumentException("Unit delivery price cannot be negative");
        if (category == null) throw new IllegalArgumentException("Category cannot be null");
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");

        this.id = generateId();
        this.name = name;
        this.unitDeliveryPrice = unitDeliveryPrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    private static synchronized int generateId() { return ++idCode; }

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

    /**
     * Sets the name of the product.
     *
     * @param name the new name of the product
     * @throws IllegalArgumentException if the name is null or empty
     */

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    /**
     * Sets the unit delivery price of the product.
     *
     * @param unitDeliveryPrice the new unit delivery price
     * @throws IllegalArgumentException if the price is negative
     */

    public void setUnitDeliveryPrice(double unitDeliveryPrice) {
        if (unitDeliveryPrice < 0) throw new IllegalArgumentException("Unit delivery price cannot be negative");
        this.unitDeliveryPrice = unitDeliveryPrice;
    }

    /**
     * Sets the category of the product.
     *
     * @param category the new category
     * @throws IllegalArgumentException if the category is null
     */
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
                ", category=" + category +
                ", expirationDate=" + expirationDate +
                ", quantity=" + quantity +
                '}';
    }
}
