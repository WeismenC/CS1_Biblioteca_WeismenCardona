package org.example;

public class Student extends Person {
    public Student(int document, String name, String lastname) {
        super(document, name, lastname,"Estudiante");
    }

    @Override
    public int getMaxLoans() {
        return 5;
    }
}
