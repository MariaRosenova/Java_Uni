package org.project;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double unitDeliveryPrice;
    private String category;
    private LocalDate expirationDate;
    private int quantity;

    // Constructor
    public Product(int id, String name, double unitDeliveryPrice, String category, LocalDate expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.unitDeliveryPrice = unitDeliveryPrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    // Method to calculate selling price
    public double calculateSellingPrice(int markupPercentage, int daysBeforeExpiration, int discountPercentage) {
        double sellingPrice = unitDeliveryPrice + (unitDeliveryPrice * markupPercentage / 100);

        LocalDate expirationPlusDays = LocalDate.now().plusDays(daysBeforeExpiration);
        if (expirationPlusDays.isAfter(expirationDate)) {
            double discountAmount = sellingPrice * discountPercentage / 100;
            sellingPrice -= discountAmount;
        }

        return sellingPrice;
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

    public String getCategory() {
        return category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitDeliveryPrice(double unitDeliveryPrice) {
        this.unitDeliveryPrice = unitDeliveryPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
