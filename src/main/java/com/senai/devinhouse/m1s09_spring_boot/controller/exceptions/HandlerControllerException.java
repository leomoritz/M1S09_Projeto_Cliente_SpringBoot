package com.senai.devinhouse.m1s09_spring_boot.controller.exceptions;

import com.senai.devinhouse.m1s09_spring_boot.controller.dto.StandardError;
import com.senai.devinhouse.m1s09_spring_boot.service.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class HandlerControllerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handle(MethodArgumentNotValidException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setClassMethod(e.getClass().descriptorString());
        error.setError("Resource is invalid");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> accountNull(NullPointerException e, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setClassMethod(e.getClass().descriptorString());
        error.setError("Resource is empty or null");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> accountNotFound(EntityNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setClassMethod(e.getClass().descriptorString());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
