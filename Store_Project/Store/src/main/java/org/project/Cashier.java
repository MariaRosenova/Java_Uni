package org.project;

import java.io.Serializable;

public class Cashier implements Serializable {
    private int id;
    private String name;
    private double salary;
    private int registerId;

    public Cashier(int id, String name, double salary) {
        this.id = id;
        this.name = name;
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
}
