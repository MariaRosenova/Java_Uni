package org.project;

import java.time.LocalDate;

public class NonFoodSellingPriceCalculator implements SellingPriceCalculator {

    @Override
    public double calculateSellingPrice(double unitDeliveryPrice, int markupPercentage, int discountPercentage, int daysBeforeExpiration, LocalDate expirationDate) {
        return unitDeliveryPrice + (unitDeliveryPrice * markupPercentage / 100);
    }
}
