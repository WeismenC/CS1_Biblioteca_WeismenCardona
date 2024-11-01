package org.exceptions;

public class DataException extends Exception {
    public DataException(String message) {
        super(message);
    }

    public static DataException documentRegistered() {
        return new DataException("Error: Documento ya registrado.");
    }

    public static DataException userNotFound() {
        return new DataException("Error: Usuario no encontrado.");
    }

    public static DataException itemNotFound() {
        return new DataException("Error: Material no encontrado.");
    }

    public static DataException invalidOption() {
        return new DataException("Error: Opción no válida.");
    }

    public static DataException necesaryUser(){
        return new DataException("Error: No hay usuarios registrados.");
    }

    public static DataException invalidUser(){
        return new DataException("Error: Usuario invalido.");
    }
}