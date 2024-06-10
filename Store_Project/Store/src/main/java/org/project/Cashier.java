package org.project;

import java.io.Serializable;

public class Cashier implements Serializable {
    private int id;
    private static int idCode = 0;
    private String name;
    private double salary;
    private int registerId;

    public Cashier(int id, String name, double salary) {
        this.name = name;
        this.id = generateId();
        this.salary = salary;
    }

    private static synchronized int generateId() { return ++idCode; }
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
    public boolean processOrder(double priceOfOrder, double customerMoney) throws InsufficientFundsException {
        if (priceOfOrder > customerMoney) {
            double shortfall = priceOfOrder - customerMoney;
            throw new InsufficientFundsException("The money is not enough, you have to pay $" + shortfall + "more.");

        } else {
            double remainder = customerMoney - priceOfOrder;
            if (remainder > 0) {
                System.out.println("The order is successful! You have $" + remainder + " in change.");}
            return true;
        }

    }

    @Override
    public String toString() {
        return "Cashier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", registerId=" + registerId +
                '}';
    }
}
