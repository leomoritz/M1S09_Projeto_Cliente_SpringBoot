package com.senai.devinhouse.m1s09_spring_boot.service.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }
}
