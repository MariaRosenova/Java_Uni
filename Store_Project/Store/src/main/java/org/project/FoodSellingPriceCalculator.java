package org.project;

import java.time.LocalDate;

public class FoodSellingPriceCalculator implements SellingPriceCalculator {
    @Override
    public double calculateSellingPrice(double unitDeliveryPrice, int markupPercentage, int discountPercentage, int daysBeforeExpiration, LocalDate expirationDate) {
        double sellingPrice = unitDeliveryPrice + (unitDeliveryPrice * markupPercentage / 100);

        LocalDate expirationPlusDays = LocalDate.now().plusDays(daysBeforeExpiration);
        if (expirationPlusDays.isAfter(expirationDate)) {
            double discountAmount = sellingPrice * discountPercentage / 100;
            sellingPrice -= discountAmount;
        }

        return sellingPrice;
    }
}
