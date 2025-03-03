package com.iesvdm.proyectoapi.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    public  NotFoundException(long id, String x) {
        super("No encontrado " + x + " con id: " + id);
    }
}
