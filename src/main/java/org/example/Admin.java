package org.example;

public class Admin extends Person{
    public Admin(int document, String name, String lastname) {
        super(document, name, lastname, "Admin");
    }

    @Override
    public int getMaxLoans() {
        return 2;
    }
}
