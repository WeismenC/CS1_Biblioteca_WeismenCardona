package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loan {
    private List<String> movementHistory = new ArrayList<>();

    public void registerLoan(Admin admin, Scanner scanner) {
        Person person = admin.selectPerson(scanner);
        Item item = admin.selectItem(scanner);
        if (person != null && item != null && canLend(person, item)) {
            item.decreaseQuantity(1);
            String motion = "Préstamo - Usuario: " + person.getDocument() + ". Material: " + item.getId();
            movementHistory.add(motion);
            System.out.println(motion);
        }
    }

    public void registerReturn(Admin admin, Scanner scanner) {
        Person person = admin.selectPerson(scanner);
        Item item = admin.selectItem(scanner);
        if (person != null && item != null) {
            item.increaseQuantity(1);
            String motion = "Devolución - Usuario: " + person.getDocument() + ". Material: " + item.getId();
            movementHistory.add(motion);
            System.out.println(motion);
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
