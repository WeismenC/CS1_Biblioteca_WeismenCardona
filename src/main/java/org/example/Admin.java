package org.example;

import org.exceptions.DataException;
import org.persistence.FileManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Person {
    private static final String PASSWORD = "admin123";
    private List<Person> people = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private FileManager<Person> personFileManager = new FileManager<>("usuarios.txt");
    private FileManager<Item> itemFileManager = new FileManager<>("materiales.txt");
    private static Admin instance;

    public Admin(int document, String name, String lastname) {
        super(document, name, lastname, "Admin");
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin(123456, "Juan", "Pérez");
        }
        return instance;
    }

    @Override
    public int getMaxLoans() {
        return 2;
    }

    public boolean authenticateAdmin(Scanner scanner) {
        System.out.print("Ingrese la contraseña de administrador: ");
        String inputPassword = scanner.next();
        return PASSWORD.equals(inputPassword);
    }

    public void addPerson(Scanner scanner) throws DataException {
        System.out.println("Seleccione el rol de la persona a agregar:");
        System.out.println("1. Estudiante. |  2. Profesor.");
        int rolOption = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nombre: ");
        String name = scanner.nextLine();
        System.out.println("Ingrese el apellido: ");
        String lastname = scanner.nextLine();
        System.out.println("Ingrese el documento: ");
        int document = scanner.nextInt();

        if (isDocumentUnique(document)) {
            Person person = rolOption == 1 ? new Student(document, name, lastname) : new Teacher(document, name, lastname);
            people.add(person);
            saveToFile("C:\\\\Users\\\\Wiche\\\\Documents\\\\GitHub\\\\Registros\\\\usuarios.txt", "Usuario registrado: " + person.getName() + " " + person.getLastname());
            personFileManager.saveData(people);
            System.out.println("Persona agregada: " + name + " " + lastname);
        } else {
            throw DataException.documentRegistered();
        }
    }

    public void addItem(Scanner scanner) throws DataException {
        System.out.println("Ingrese el ID del material: ");
        String id = scanner.next();
        scanner.nextLine();
        System.out.println("Ingrese el título del material: ");
        String title = scanner.nextLine();
        System.out.println("Ingrese la fecha de registro dd/mm/aaaa: ");
        String date = scanner.nextLine();
        System.out.println("Ingrese la cantidad registrada: ");
        int quantity = scanner.nextInt();

        Item item = new Item(id, title, date, quantity);
        items.add(item);
        saveToFile("C:\\\\Users\\\\Wiche\\\\Documents\\\\GitHub\\\\Registros\\\\materiales.txt", "Material registrado: " + item.getTitle());
        itemFileManager.saveData(items);
        System.out.println("Material agregado: " + title);
    }

    private void saveToFile(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    private boolean isDocumentUnique(int document) {
        return people.stream().noneMatch(p -> p.getDocument() == document);
    }

    public Person selectPerson(Scanner scanner) throws DataException {
        if (people.isEmpty()) {
            throw DataException.userNotFound();
        } else {
        System.out.println("Seleccione el ID de la persona: ");
        return people.get(0);
        }
    }

    public Item selectItem(Scanner scanner) throws DataException {
        if (items.isEmpty()) {
            throw DataException.itemNotFound();
        } else {
        System.out.println("Seleccione el ID del material: ");
        return items.get(0);
        }
    }

    public void deleteItem(Scanner scanner) {
        System.out.println("Ingrese el ID del material a eliminar: ");
        String id = scanner.next();
        items.removeIf(i -> i.getId().equals(id));
        System.out.println("Material eliminado.");
    }

    public void deletePerson(Scanner scanner) {
        System.out.println("Ingrese el documento de la persona a eliminar: ");
        int document = scanner.nextInt();
        people.removeIf(p -> p.getDocument() == document);
        System.out.println("Persona eliminada.");
    }

    public void showRegisteredPeople() {
        if (people.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            System.out.println("Personas registradas: ");
            for (Person person : people) {
                System.out.println(person.getDocument() + ": " + person.getName() + " " + person.getLastname() + " (" + person.getRol() + ")");
            }
        }
    }

    public void showRegisteredItems() {
        if (items.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros registrados: ");
            for (Item item : items) {
                System.out.println(item.getId() + " " + item.getTitle() + " " + item.getRegisteredDate() + " " + item.getCurrentAmount());
            }
        }
    }

    public boolean hasPeople() {
        return !people.isEmpty();
    }    
}
