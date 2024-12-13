package org.persistence;

import org.exceptions.DataException;
import org.example.Item;
import org.example.Person;
import org.example.Student;
import org.example.Teacher;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager<T> {
    private String filePath;

    public FileManager(String fileName) {
        String baseDirectory = "C:\\Users\\Wiche\\Documents\\GitHub\\Registros"; 
        File directory = new File(baseDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        this.filePath = baseDirectory + File.separator + fileName;
    }

    public void saveData(List<T> data) throws DataException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (T item : data) {
                writer.write(convertToString(item) + "\n");
            }
        } catch (IOException e) {
            throw new DataException("Error al guardar los datos: " + e.getMessage());
        }
    }

    public List<T> loadData() throws DataException {
        List<T> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(convertFromString(line));
            }
        } catch (IOException e) {
            throw new DataException("Error al cargar los datos: " + e.getMessage());
        }
        return data;
    }

    private String convertToString(T item) {
        if (item instanceof Person) {
            Person person = (Person) item;
            return "Documento:" + person.getDocument() + " | Nombre:" + person.getName() + " | Apellido:" + person.getLastname() + " | Rol:" + person.getRol();
        } else if (item instanceof Item) {
            Item itemObj = (Item) item;
            return "ID:" + itemObj.getId() + " | Titulo:" + itemObj.getTitle() + " | Fecha registro:" + itemObj.getRegisteredDate() + " | Cantidad:" + itemObj.getCurrentAmount();
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    private T convertFromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            if (filePath.endsWith("people.dat")) { 
                int document = Integer.parseInt(parts[0]);
                String name = parts[1];
                String lastname = parts[2];
                String rol = parts[3];
                return (T) ("Estudiante".equals(rol) ? new Student(document, name, lastname) : new Teacher(document, name, lastname));
            } else if (filePath.endsWith("items.dat")) {
                String id = parts[0];
                String title = parts[1];
                String registeredDate = parts[2];
                int quantity = Integer.parseInt(parts[3]);
                return (T) new Item(id, title, registeredDate, quantity);
            }
        }
        return null;
    }
}
