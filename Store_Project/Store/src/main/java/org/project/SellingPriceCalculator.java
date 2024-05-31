package org.project;

import java.time.LocalDate;

public interface SellingPriceCalculator {
    double calculateSellingPrice(double unitDeliveryPrice, int markupPercentage, int discountPercentage, int daysBeforeExpiration, LocalDate expirationDate);
}