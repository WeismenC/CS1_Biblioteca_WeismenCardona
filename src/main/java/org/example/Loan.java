package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.exceptions.DataException;

public class Loan implements ILoan {

    private List<String> movementHistory = new ArrayList<>();

    @Override
    public void registerLoan(Admin admin, Scanner scanner) throws IOException, DataException {
        Person person = admin.selectPerson(scanner);
        Item item = admin.selectItem(scanner);
        if (person != null && item != null && canLend(person, item)) {
            item.decreaseQuantity(1);
            String motion = "Préstamo - Usuario: " + person.getLastname() + ". Material: " + item.getTitle();
            movementHistory.add(motion);
            saveToFile("C:\\Users\\Wiche\\Documents\\GitHub\\Prestamos\\prestamos.txt", motion);
            System.out.println(motion);
        }else {
            throw DataException.necesaryUser();
        }
    }

    @Override
    public void registerReturn(Admin admin, Scanner scanner) throws IOException, DataException {
        Person person = admin.selectPerson(scanner);
        Item item = admin.selectItem(scanner);
        if (person != null && item != null) {
            item.increaseQuantity(1);
            String motion = "Devolución - Usuario: " + person.getLastname() + ". Material: " + item.getTitle();
            movementHistory.add(motion);
            saveToFile("C:\\Users\\Wiche\\Documents\\GitHub\\Prestamos\\devoluciones.txt", motion);
            System.out.println(motion);
        }else {
            throw DataException.necesaryUser();
        }
    }

    private void saveToFile(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    private boolean canLend(Person person, Item item) {
        if (item.getCurrentAmount() > 0 && person.getMaxLoans() > 0) {
            return true;
        } else if (item.getCurrentAmount() == 0) {
            System.out.println("No hay suficiente material para prestar.");
            return false;
        } else {
            System.out.println("Este usuario ha alcanzado su máximo de préstamos.");
            return false;
        }
    }

    @Override
    public void checkHistory() {
        if (movementHistory.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
        } else {
            System.out.println("Historial de movimientos:");
            for (String motion : movementHistory) {
                System.out.println(motion);
            }
        }
    }
}
