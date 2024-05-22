package org.project;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements  Serializable{
    private int id;
    private String name;
    private double purchasePrice;
    private String category;
    private LocalDate expirationDate;
    private int quantity;

    public Product(int id, String name, double purchasePrice, String category, LocalDate expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public double calculateSellingPrice(int markupPercentage, int daysBeforeExpiration, int discountPercentage) {
        double markupAmount = purchasePrice * markupPercentage / 100;
        double sellingPrice = purchasePrice + markupAmount;

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

    public double getPurchasePrice() {
        return purchasePrice;
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

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
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
