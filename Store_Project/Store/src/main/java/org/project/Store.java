package org.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private static int nextReceiptNumber = 1;
    private Map<Integer, Cashier> cashiers;
    private List<Product> inventory;
    private Map<Integer, Receipt> receipts;
    private double totalRevenue;
    private double totalExpenses;

    public Store() {
        this.cashiers = new HashMap<>();
        this.inventory = new ArrayList<>();
        this.receipts = new HashMap<>();
        this.totalRevenue = 0.0;
        this.totalExpenses = 0.0;
    }

    public List<Cashier> getCashiers() {
        return new ArrayList<>(cashiers.values());
    }

    public void addCashier(Cashier cashier) {
        this.cashiers.put(cashier.getId(), cashier);
        this.totalExpenses += cashier.getSalary();
    }
    public void removeCashier(int cashierId) {
        Cashier removed = this.cashiers.remove(cashierId);
        if (removed != null) {
            this.totalExpenses -= removed.getSalary();
        }
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public void setInventory(List<Product> inventory) {
        this.inventory = inventory;
    }

    public List<Receipt> getReceipts() {
        return new ArrayList<>(receipts.values());
    }

    public synchronized void addReceipts(Receipt receipt) {
        this.receipts.put(nextReceiptNumber++, receipt);
        this.totalRevenue += receipt.getTotalAmount();
    }

    public synchronized void addProduct(Product product) {
        this.inventory.add(product);
        this.totalExpenses += product.getUnitDeliveryPrice();
    }

    public synchronized void removeProduct(Product product) {
        this.inventory.remove(product);
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getProfit() {
        return totalRevenue - totalExpenses;
    }

}
