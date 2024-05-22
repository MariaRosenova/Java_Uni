package org.project;

public class InsufficientQuantityException extends Exception {
    private Product product;
    private int requestedQuantity;
    private int availableQuantity;

    public InsufficientQuantityException(Product product, int requestedQuantity, int availableQuantity) {
        super("Insufficient quantity for product: " + product.getName() + ". Requested: " + requestedQuantity + ", Available: " + availableQuantity);
        this.product = product;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
