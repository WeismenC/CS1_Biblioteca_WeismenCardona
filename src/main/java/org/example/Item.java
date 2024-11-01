package org.example;

public class Item {
    private String Id;
    private String Title;
    private String registeredDate;
    private int registeredAmount;
    private int currentAmount;

    public Item(String id, String title, String registeredDate, int registeredAmount) {
        this.Id = id;
        this.Title = title;
        this.registeredDate = registeredDate;
        this.registeredAmount = registeredAmount;
        this.currentAmount = registeredAmount;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getRegisteredAmount() {
        return registeredAmount;
    }

    public void setRegisteredAmount(int registeredAmount) {
        this.registeredAmount = registeredAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void increaseQuantity(int quantity) {
        this.currentAmount += quantity;
    }

    public void decreaseQuantity(int quantity) {
        if (this.currentAmount >= quantity) {
            this.currentAmount -= quantity;
        } else {
            System.out.println("No hay suficientes materiales disponibles.");
        }
    }
}