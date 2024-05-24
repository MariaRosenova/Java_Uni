package org.project;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static int nextReceiptNumber = 1;
    private List<Cashier> cashiers;
    private List<Product> inventory;
    private List<Receipt> receipts;
    private double totalRevenue;
    private double totalExpenses;

    public Store() {
        this.cashiers = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.totalRevenue = 0.0;
        this.totalExpenses = 0.0;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public void setInventory(List<Product> inventory) {
        this.inventory = inventory;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public void addProduct(Product product) {
        this.inventory.add(product);
       // this.totalExpenses += product.getDeliveryCost(); //FIX: THE PRODUCT DELIVERY
    }

    public void removeProduct(Product product) {
        this.inventory.remove(product);
    }

    public void addCashier(Cashier cashier) {
        this.cashiers.add(cashier);
        this.totalExpenses += cashier.getSalary(); // See this method
    }

    public void removeCashier(Cashier cashier) {
        this.cashiers.remove(cashier);
        this.totalExpenses -= cashier.getSalary();
    }

    public Receipt processPurchase(List<Product> products, Cashier cashier) {
        double purchaseTotal = 0.0;
        for (Product product : products) {
            if (this.inventory.contains(product)) {
                purchaseTotal += product.getPurchasePrice();
                this.inventory.remove(product);
            }
        }
        //cashier, products, purchaseTotal
        Receipt receipt = new Receipt(nextReceiptNumber++, cashier, products, purchaseTotal);
        this.receipts.add((receipt));

        return receipt;
    }
}
