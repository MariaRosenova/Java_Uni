package org.project;

import java.time.LocalDate;

public class ExpiredProductException extends Exception {
    private Product product;

    public ExpiredProductException(Product product) {
        super("Product expired: " + product.getName() + " with expiration date: " + product.getExpirationDate());
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
