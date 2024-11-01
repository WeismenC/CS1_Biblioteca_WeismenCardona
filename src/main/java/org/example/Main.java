package org.example;

import java.io.IOException;
import java.util.Scanner;
import org.exceptions.DataException;

public class Main {
    public static void main(String[] args) throws DataException, IOException {
        Scanner scanner = new Scanner(System.in);

        Admin admin = Admin.getInstance();
        ILoan loan = new Loan();

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
            System.out.println("9. Mostrar libros registrados");
            System.out.println("0. Salir");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    admin.addPerson(scanner);
                    break;
                case 2:
                    if (admin.hasPeople()) {
                        loan.registerLoan(admin, scanner);
                    } else {
                        throw DataException.userNotFound();
                    }
                    break;
                case 3:
                    if (admin.hasPeople()) {
                        loan.registerReturn(admin, scanner);
                    } else {
                        throw DataException.userNotFound();
                    }
                    break;
                case 4:
                    if (admin.authenticateAdmin(scanner)) {
                        admin.addItem(scanner);
                    } else {
                        throw DataException.invalidUser();
                    }
                    break;
                case 5:
                    if (admin.authenticateAdmin(scanner)) {
                        admin.deleteItem(scanner);
                    } else {
                        throw DataException.invalidUser();
                    }
                    break;
                case 6:
                    loan.checkHistory();
                    break;
                case 7:
                    if (admin.authenticateAdmin(scanner)) {
                        admin.deletePerson(scanner);
                    } else {
                        throw DataException.invalidUser();
                    }
                    break;
                case 8:
                    admin.showRegisteredPeople();
                    break;
                case 9:
                    admin.showRegisteredItems();
                    break;
                case 0:
                    System.out.println("Finalizado.");
                    exit = true;
                    break;
                default:
                    throw DataException.invalidOption();
            }
        }
    }
}
