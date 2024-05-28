package org.project;

import java.io.Serializable;

public class Cashier implements Serializable {
    private int id;
    private String name;
    private double salary;
    private int registerId;

    public Cashier(int id, String name, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getRegisterId() {
        return registerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public void processOrder(double priceOfOrder, double customerMoney) {
        if (priceOfOrder > customerMoney) {
            double shortfall = priceOfOrder - customerMoney;
            System.out.println("The money is not enough, you have to pay $" + shortfall + "more.");
        } else {
            double remainder = customerMoney - priceOfOrder;
            String message = remainder > 0
                    ? "The order is successful! You have $" + remainder + " in change."
                    : "The order is successful!";
            System.out.println(message);
        }

    }
}
