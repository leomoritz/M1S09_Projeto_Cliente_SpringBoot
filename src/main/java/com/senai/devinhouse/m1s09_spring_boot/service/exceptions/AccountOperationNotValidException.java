package com.senai.devinhouse.m1s09_spring_boot.service.exceptions;

public class AccountOperationNotValidException extends RuntimeException {

    public AccountOperationNotValidException(String message) {
        super(message);
    }
}
