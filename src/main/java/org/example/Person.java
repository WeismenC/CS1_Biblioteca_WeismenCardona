package org.example;

public class Person{
    private int document;
    private String name;
    private String lastname;
    private String rol;

    public Person(int document, String name, String lastname, String rol){
        this.document = document;
        this.name = name;
        this.lastname = lastname;
        this.rol = rol;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getMaxLoans() {
        return 0;
    }
}