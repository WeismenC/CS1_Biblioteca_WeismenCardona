package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin(123456, "Juan", "Pérez");

        Loan loan = new Loan();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Crear usuario (Estudiante/Profesor)");
            System.out.println("2. Registrar un préstamo");
            System.out.println("3. Registrar una devolución");
            System.out.println("4. Agregar material (solo Admin)");
            System.out.println("5. Eliminar material (solo Admin)");
            System.out.println("6. Consultar historial de préstamos");
            System.out.println("7. Eliminar persona (solo Admin)");
            System.out.println("8. Mostrar personas registradas");
            System.out.println("9. Mostrar libros registrados.");
            System.out.println("0. Salir");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    admin.addPerson(scanner);
                    break;
                case 2:
                    loan.registerLoan(admin, scanner);
                    break;
                case 3:
                    loan.registerReturn(admin, scanner);
                    break;
                case 4:
                    admin.addItem(scanner);
                    break;
                case 5:
                    admin.deleteItem(scanner);
                    break;
                case 6:
                    loan.checkHistory();
                    break;
                case 7:
                    admin.deletePerson(scanner);
                    break;
                case 8:
                    admin.showRegisteredPeople();
                    break;
                case 9:
                    admin.showRegisteredItems();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
