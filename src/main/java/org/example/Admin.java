package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Person {
    private List<Person> people = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public Admin(int document, String name, String lastname) {
        super(document, name, lastname, "Admin");
    }

    @Override
    public int getMaxLoans() {
        return 2;
    }

    public void addPerson(Scanner scanner) {
        System.out.println("Seleccione el rol de la persona a agregar:");
        System.out.println("1. Estudiante");
        System.out.println("2. Profesor");

        int rolOption = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre:");
        String name = scanner.nextLine();
        System.out.println("Ingrese el apellido:");
        String lastname = scanner.nextLine();
        System.out.println("Ingrese el documento:");
        int document = scanner.nextInt();

        if (isDocumentUnique(document)) {
            Person person;
            if (rolOption == 1) {
                person = new Student(document, name, lastname);
            } else {
                person = new Teacher(document, name, lastname);
            }
            people.add(person);
            System.out.println("Persona agregada: " + name + " " + lastname);
        } else {
            System.out.println("El documento ya está registrado. No se puede agregar.");
        }
    }

    private boolean isDocumentUnique(int document) {
        for (Person person : people) {
            if (person.getDocument() == document) {
                return false;
            }
        }
        return true;
    }

    public void deletePerson(Scanner scanner) {
        System.out.println("Ingrese el documento de la persona a eliminar:");
        int document = scanner.nextInt();
        people.removeIf(p -> p.getDocument() == document);
        System.out.println("Persona eliminada.");
    }

    public void showRegisteredPeople() {
        if (people.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            System.out.println("Personas registradas:");
            for (Person person : people) {
                System.out.println(person.getDocument() + ": " + person.getName() + " " + person.getLastname() + " (" + person.getRol() + ")");
            }
        }
    }

    public void showRegisteredItems(){
        if(items.isEmpty()){
            System.out.println("No hay libros registrados.");
        }else{
            System.out.println("Libros registrados:");
            for(Item item : items) {
                System.out.println(item.getId() + " " + item.getTitle() + " " + item.getRegisteredDate() + " " + item.getCurrentAmount());
            }
        }
    }

    public void addItem(Scanner scanner) {
        System.out.println("Ingrese el ID del material:");
        String id = scanner.next();
        System.out.println("Ingrese el título del material:");
        String title = scanner.next();
        System.out.println("Ingrese la fecha de registro dd/mm/aaaa:");
        String date = scanner.next();
        System.out.println("Ingrese la cantidad registrada:");
        int quantity = scanner.nextInt();

        if (isIdUnique(id)) {
            Item item = new Item(id, title, date, quantity);
            items.add(item);
            System.out.println("Material agregado: " + title);
        } else {
            System.out.println("El ID del material ya está registrado. No se puede agregar.");
        }
    }

    private boolean isIdUnique(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public void deleteItem(Scanner scanner) {
        System.out.println("Ingrese el ID del material a eliminar:");
        String id = scanner.next();
        items.removeIf(i -> i.getId().equals(id));
        System.out.println("Material eliminado.");
    }

    public Person selectPerson(Scanner scanner) {
        if (people.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return null;
        }
        System.out.println("Ingrese el documento de la persona:");
        int document = scanner.nextInt();
        for (Person person : people) {
            if (person.getDocument() == document) {
                return person;
            }
        }
        System.out.println("Persona no encontrada.");
        return null;
    }

    public Item selectItem(Scanner scanner) {
        if (items.isEmpty()) {
            System.out.println("No hay materiales registrados.");
            return null;
        }
        System.out.println("Ingrese el ID del material:");
        String id = scanner.next();
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        System.out.println("Material no encontrado.");
        return null;
    }
}
