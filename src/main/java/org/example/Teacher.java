package org.example;

public class Teacher extends Person {
    public Teacher(int document, String name, String lastname) {
        super(document, name, lastname, "Docente");
    }

    @Override
    public int getMaxLoans() {
        return 3;
    }
}