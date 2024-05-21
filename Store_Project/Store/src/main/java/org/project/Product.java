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


}
